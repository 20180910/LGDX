package com.sk.lgdx.module.home.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.rx.RxBus;
import com.github.customview.MyTextView;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.BaseObj;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.adapter.AddImgAdapter;
import com.sk.lgdx.module.home.event.AddImgEvent;
import com.sk.lgdx.module.home.event.MyHomeWorkEvent;
import com.sk.lgdx.module.home.network.request.QuerentijiaoBody;
import com.sk.lgdx.module.my.network.request.UploadImgBody;
import com.sk.lgdx.module.study.Constant;
import com.sk.lgdx.network.ApiRequest;
import com.sk.lgdx.tools.BitmapUtils;
import com.sk.lgdx.tools.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import top.zibin.luban.Luban;

/**
 * Created by Administrator on 2017/12/22.
 */

public class QuerentijiaoActivity extends BaseActivity {
    @BindView(R.id.et_querentijiao_fankui)
    EditText et_querentijiao_fankui;
    @BindView(R.id.rv_querentijiao_img)
    RecyclerView rv_querentijiao_img;
    @BindView(R.id.tv_querentijiao)
    MyTextView tv_querentijiao;
    AddImgAdapter addImgAdapter;
    private BottomSheetDialog selectPhotoDialog;
    private int selectImgIndex;
    String operation_id,content="";


    @Override
    protected int getContentView() {
        setAppTitle("确认提交");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_querentijiao;
    }

    @Override
    protected void initView() {
        getValue();
        addImgAdapter=new AddImgAdapter(mContext,R.layout.item_fatie_addimg);
        addImgAdapter.setList(new ArrayList());
        rv_querentijiao_img.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        rv_querentijiao_img.setAdapter(addImgAdapter);

    }

    private void getValue() {
        operation_id=getIntent().getStringExtra(Constant.IParam.operation_id);

    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(AddImgEvent.class, new MySubscriber<AddImgEvent>() {
            @Override
            public void onMyNext(AddImgEvent event) {
                selectImgIndex = event.selectImgIndex;
                showSelectPhotoDialog();
            }
        });
    }
    private void showSelectPhotoDialog() {
        if (selectPhotoDialog == null) {
            View sexView= LayoutInflater.from(mContext).inflate(R.layout.popu_select_photo,null);
            sexView.findViewById(R.id.tv_select_photo).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                    selectPhoto();
                }
            });
            sexView.findViewById(R.id.tv_take_photo).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                    takePhoto();
                }
            });
            sexView.findViewById(R.id.tv_photo_cancle).setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    selectPhotoDialog.dismiss();
                }
            });
            selectPhotoDialog = new BottomSheetDialog(mContext);
            selectPhotoDialog.setCanceledOnTouchOutside(true);
            selectPhotoDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            selectPhotoDialog.setContentView(sexView);
        }
        selectPhotoDialog.show();
    }
    //选择相册
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3000);
    }
    private String path = Environment.getExternalStorageDirectory() +
            File.separator + Environment.DIRECTORY_DCIM + File.separator;
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "IMG_" + dateFormat.format(date);
    }
    Uri photoUri;
    private String imgSaveName="";
    //拍照
    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String fileName = getPhotoFileName() + ".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imgSaveName = path + fileName;
                photoUri = Uri.fromFile(new File(imgSaveName));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 2000);
            }
        }
    }
    private void uploadImg() {
        showLoading();
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String newPath= ImageUtils.filePath;
                ImageUtils.makeFolder(newPath);
                FileInputStream fis = null;
                try {
                    List<File> files = Luban.with(mContext).load(imgSaveName).get();
                    String imgStr = BitmapUtils.bitmapToString2(files.get(0));
                    subscriber.onNext(imgStr);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onMyNext(String baseImg) {
                UploadImgBody item=new UploadImgBody();
                item.setFile(baseImg);
                String rnd = getRnd();
                Map<String,String> map=new HashMap<String,String>();
                map.put("rnd",rnd);
                map.put("sign", GetSign.getSign(map));
                ApiRequest.uploadImg(map,item, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        if(selectImgIndex ==-1){
                            if(isEmpty(addImgAdapter.getList())){
                                List<String> list=new ArrayList<String>();
                                list.add(obj.getImg());
                                addImgAdapter.setList(list);
                            }else{
                                addImgAdapter.getList().add(obj.getImg());
                            }
                        }else{
                            addImgAdapter.getList().set(selectImgIndex,obj.getImg());
                        }
                        addImgAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                dismissLoading();
                showToastS("图片处理失败");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){
            return;
        }
        switch (requestCode){
            case 2000:
                uploadImg();
                break;
            case 3000:
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null,null);
                if (cursor != null && cursor.moveToFirst()) {
                    imgSaveName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    uploadImg();
                }
                break;
        }
    }





    @OnClick({R.id.tv_querentijiao})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_querentijiao:
                postOperationSubmit();
            break;
        }
    }

    private void postOperationSubmit() {
        content=getSStr(et_querentijiao_fankui);
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("operation_id",operation_id);
        map.put("content",content);
        map.put("sign",GetSign.getSign(map));


        List< QuerentijiaoBody> imgBean=new ArrayList<>();
        if(notEmpty(addImgAdapter.getList())){
            for (int i = 0; i < addImgAdapter.getList().size(); i++) {
                QuerentijiaoBody body=new QuerentijiaoBody();
                body.setImages((String)addImgAdapter.getList().get(i));
                imgBean.add(body);
            }
        }
        com.sk.lgdx.module.home.network.ApiRequest.postOperationSubmit(map, imgBean, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                RxBus.getInstance().post(new MyHomeWorkEvent());
                finish();




            }
        });

    }
}
