package com.sample.yl.sampledemo.popupindow;

/**
 * Created by ${jz} on 2016/11/16.
 * 更改密码的Dialog类
 */

// public class ChangeCodeDialog extends MyDialog implements View.OnClickListener {
//    private Button bt_confirm_update, bt_suppression;
//    private EditText et_brut_code, et_update_code, et_encore_code;
//    private Activity mContext;
//    private RPCOut<Boolean> result;
//
//    public ChangeCodeDialog(@NonNull Activity context) {
//        super(context);
//        mContext = context;
//    }
//
//    public ChangeCodeDialog(@NonNull Activity context, @StyleRes int themeResId) {
//        super(context, themeResId);
//        mContext = context;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        super.setScreenSize(Gravity.CENTER, 0.9f, 0f);
//        setCancelable(false);
//    }
//
//    @Override
//    protected void init() {
//        setContentView(R.layout.change_code_dialog);
//        result = new RPCOut<>();
//    }
//
//    @Override
//    protected void initControls() {
//        et_brut_code = (EditText) findViewById(R.id.et_brut_code);
//        et_update_code = (EditText) findViewById(R.id.et_update_code);
//        et_encore_code = (EditText) findViewById(R.id.et_encore_code);
//        bt_confirm_update = (Button) findViewById(R.id.bt_confirm_update);
//        bt_suppression = (Button) findViewById(R.id.bt_suppression);
//    }
//
//    @Override
//    protected void regListener() {
//        bt_confirm_update.setOnClickListener(this);
//        bt_suppression.setOnClickListener(this);
//    }
//
//    //region  访问网络的handle
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                Toast.makeText(mContext, "网络请求失败。", Toast.LENGTH_LONG).show();
//            } else {
//                Type typelist = new TypeToken<RPCOut<Boolean>>() {
//                }.getType();
//                result = Status.gson.fromJson(msg.obj.toString(), typelist);
//                if (result.Success) {
//                    Toast.makeText(mContext, "密码修改成功!", Toast.LENGTH_SHORT).show();
//                    UserCommon.Logout(mContext);
//                } else {
//                    Toast.makeText(mContext, result.Message, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    };
//    //endregion
//
//    /**
//     * 得到用户输入的数据
//     */
//    private void getEditTextData() {
//        String brutPassword = et_brut_code.getText().toString();//旧密码
//        String updatePassword = et_update_code.getText().toString();//新密码
//        String encorePassword = et_encore_code.getText().toString();//确认新密码
//
//        if (TextUtils.isEmpty(brutPassword) || TextUtils.isEmpty(updatePassword) || TextUtils.isEmpty(encorePassword)) {
//            Toast.makeText(mContext, "密码不能空！", Toast.LENGTH_SHORT).show();
//        } else if (brutPassword.length() < 6) {
//            Toast.makeText(mContext, "密码至少6位！", Toast.LENGTH_SHORT).show();
//        } else {
//            if (updatePassword.equals(encorePassword)) {
//                String strPwd[] = new String[]{brutPassword, updatePassword};
//                submitData(strPwd);
//            } else {
//                Toast.makeText(mContext, "新密码与确认的新密码不一致！", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    /**
//     * 提交数据到服务器。
//     */
//    private void submitData(String[] strPwd) {
//        UserV1WebApi.MotifyLoginPWD(mContext, strPwd, handler);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_confirm_update:
//                getEditTextData();
//                break;
//            case R.id.bt_suppression:
//                dismiss();
//                break;
//            default:
//                break;
//        }
//    }
//}
