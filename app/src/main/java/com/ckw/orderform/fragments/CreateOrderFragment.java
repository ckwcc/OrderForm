package com.ckw.orderform.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ckw.orderform.R;
import com.ckw.orderform.base.BaseFragment;
import com.ckw.orderform.presenter.OrderContract;
import com.ckw.orderform.presenter.OrderPresenter;
import com.ckw.orderform.repository.OrderBean;
import com.ckw.orderform.repository.OrderListBean;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2018/3/20.
 */

public class CreateOrderFragment extends BaseFragment implements OrderContract.View{

    private String trim;

    public static CreateOrderFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CreateOrderFragment fragment = new CreateOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public CreateOrderFragment() {
    }

    @Inject
    OrderPresenter mPresenter;

    @BindView(R.id.et_customer)
    EditText mEtCustomer;//客户名称
    @BindView(R.id.et_classify)
    EditText mEtClassify;//种类
    @BindView(R.id.et_number)
    EditText mEtNumber;//数量
    @BindView(R.id.et_unit_price)
    EditText mEtUnitPrice;//单价
    @BindView(R.id.et_layer)
    EditText mEtLayer;//层数
    @BindView(R.id.et_box_size)
    EditText mEtBoxSize;//箱尺寸
    @BindView(R.id.et_plank_size)
    EditText mEtPlankSize;//板尺寸
    @BindView(R.id.et_line_size)
    EditText mEtLineSize;//压线尺寸
    @BindView(R.id.et_configuration)
    EditText mEtConfiguration;//配置
    @BindView(R.id.spinner_printing)
    Spinner mSpinnerPrint;//是否打印
    @BindView(R.id.rl_do_printing_container)
    RelativeLayout mDoPrintContainer;//打印内容的父布局
    @BindView(R.id.et_do_printing)
    EditText mEtDoPrint;//打印的内容
    @BindView(R.id.btn_submit)
    Button mSubmit;//提交

    @BindView(R.id.ll_star_container)
    LinearLayout mStarContainer;
    @BindView(R.id.ll_sun_container)
    LinearLayout mSunContainer;
    @BindView(R.id.tv_reset_priority)
    TextView mResetPriority;

    private boolean mShouldPrint;//记录是否需要打印
    private int mPriority;
    private int mStarPriotity;
    private int mSunPriotity;


    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_create_order;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

    }

    @Override
    protected void initListener() {
        setStarLight();
        setSunLight();
        mSpinnerPrint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("----", "onItemSelected: 点击的位置："+i);
                if(i == 1){//点击了“是”
                    mDoPrintContainer.setVisibility(View.VISIBLE);
                    mShouldPrint = true;
                }else {// 0 请选择 2 否
                    mDoPrintContainer.setVisibility(View.GONE);
                    mShouldPrint = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mShouldPrint = false;
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkOrderParams()){
                    addOrderParams();
                }
            }
        });

        mResetPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSunUnLight();
                setStarUnLight();
                mSunPriotity = 0;
                mStarPriotity = 0;
                mPriority = 0;
            }
        });
    }

    @Override
    public void getAllOrderSuccess(OrderListBean data) {
    }

    @Override
    public void addOrderSuccess(String msg) {
        Toast.makeText(getContext(),"新增订单成功",Toast.LENGTH_SHORT).show();
        initLayoutView();
    }

    @Override
    public void deleteSuccess(String msg) {

    }

    @Override
    public void updateSuccess(String msg) {

    }


    @Override
    public void showLoginFailure(String msg) {
        Log.d("----", "showLoginFailure: "+msg);
    }

    private void initLayoutView(){
        mEtCustomer.setText("");//客户名称
        mEtClassify.setText("");//种类
        mEtNumber.setText("");//数量
        mEtUnitPrice.setText("");//单价
        mEtLayer.setText("");//层数
        mEtBoxSize.setText("");//箱尺寸
        mEtPlankSize.setText("");//板尺寸
        mEtLineSize.setText("");//压线尺寸
        mEtConfiguration.setText("");//配置
        mEtDoPrint.setText("");//打印的内容
        mShouldPrint = false;
        mSpinnerPrint.setSelection(0);
        mPriority = 0;
        mStarPriotity = 0;
        mSunPriotity = 0;
        setStarUnLight();
        setSunUnLight();
    }

    private void addOrderParams(){
        OrderBean orderBean = new OrderBean();
        orderBean.setCustomerName(mEtCustomer.getText().toString().trim());
        orderBean.setKind(mEtClassify.getText().toString().trim());
        orderBean.setNum(Integer.parseInt(mEtNumber.getText().toString().trim()));
        orderBean.setPrice(Double.parseDouble(mEtUnitPrice.getText().toString().trim()));
        orderBean.setCeng(mEtLayer.getText().toString().trim());
        orderBean.setSizeXiang(mEtBoxSize.getText().toString().trim());
        orderBean.setSizeBan(mEtPlankSize.getText().toString().trim());
        orderBean.setSizeYa(mEtLineSize.getText().toString().trim());
        orderBean.setPeizhi(mEtConfiguration.getText().toString().trim());
        if(!mShouldPrint){
            orderBean.setIsYinshua(0);
        }else {
            orderBean.setIsYinshua(1);
            orderBean.setYinshuaText(mEtDoPrint.getText().toString().trim());
        }
        //未完成
        orderBean.setState(0);

        mPriority = mSunPriotity * 10 + mStarPriotity;
        Log.d("----", "addOrderParams: 优先级："+mPriority);
        orderBean.setPriority(mPriority);

        Gson gson = new Gson();
        String toJson = gson.toJson(orderBean);
        Log.d("flag", "----------------->addOrderParams: 上传的数据：" +toJson);
        mPresenter.addOrder(toJson);
    }

    private boolean checkOrderParams(){
        if(mEtCustomer.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写客户名称",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mEtClassify.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写种类",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mEtNumber.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写数量",Toast.LENGTH_SHORT).show();

            return false;
        }else {
            String trim = mEtNumber.getText().toString().trim();
            try {
                Integer.parseInt(trim);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(),"请填写正确的数量",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(mEtUnitPrice.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写单价",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            String trim = mEtUnitPrice.getText().toString().trim();
            try {
                Double.parseDouble(trim);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(),"请填写正确的单价",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(mEtLayer.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写层数",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mEtBoxSize.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写箱尺寸",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mEtPlankSize.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写板尺寸",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mEtLineSize.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写压线尺寸",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mEtConfiguration.getText().toString().trim().equals("")){
            Toast.makeText(getContext(),"请填写配置",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(mShouldPrint){
            if(mEtDoPrint.getText().toString().trim().equals("")){
                Toast.makeText(getContext(),"请填写打印的内容",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void setStarUnLight(){
        ImageView starOne = (ImageView)mStarContainer.findViewById(R.id.iv_star_one);
        ImageView starTwo = (ImageView)mStarContainer.findViewById(R.id.iv_star_two);
        ImageView starThree = (ImageView)mStarContainer.findViewById(R.id.iv_star_three);
        ImageView starFour = (ImageView)mStarContainer.findViewById(R.id.iv_star_four);
        ImageView starFive = (ImageView)mStarContainer.findViewById(R.id.iv_star_five);

        starOne.setImageResource(R.mipmap.ic_score_unchecked);
        starTwo.setImageResource(R.mipmap.ic_score_unchecked);
        starThree.setImageResource(R.mipmap.ic_score_unchecked);
        starFour.setImageResource(R.mipmap.ic_score_unchecked);
        starFive.setImageResource(R.mipmap.ic_score_unchecked);
    }

    private void setSunUnLight(){
        ImageView sunOne = mSunContainer.findViewById(R.id.iv_sun_one);
        ImageView sunTwo = mSunContainer.findViewById(R.id.iv_sun_two);
        ImageView sunThree = mSunContainer.findViewById(R.id.iv_sun_three);
        ImageView sunFour = mSunContainer.findViewById(R.id.iv_sun_four);
        ImageView sunFive = mSunContainer.findViewById(R.id.iv_sun_five);

        sunOne.setImageResource(R.mipmap.ic_sun_unchecked);
        sunTwo.setImageResource(R.mipmap.ic_sun_unchecked);
        sunThree.setImageResource(R.mipmap.ic_sun_unchecked);
        sunFour.setImageResource(R.mipmap.ic_sun_unchecked);
        sunFive.setImageResource(R.mipmap.ic_sun_unchecked);
    }

    private void setStarLight(){
        final ImageView starOne = (ImageView)mStarContainer.findViewById(R.id.iv_star_one);
        final ImageView starTwo = (ImageView)mStarContainer.findViewById(R.id.iv_star_two);
        final ImageView starThree = (ImageView)mStarContainer.findViewById(R.id.iv_star_three);
        final ImageView starFour = (ImageView)mStarContainer.findViewById(R.id.iv_star_four);
        final ImageView starFive = (ImageView)mStarContainer.findViewById(R.id.iv_star_five);

        starOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageResource(R.mipmap.ic_score_checked);
                starTwo.setImageResource(R.mipmap.ic_score_unchecked);
                starThree.setImageResource(R.mipmap.ic_score_unchecked);
                starFour.setImageResource(R.mipmap.ic_score_unchecked);
                starFive.setImageResource(R.mipmap.ic_score_unchecked);
                mStarPriotity = 1;
            }
        });
        starTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageResource(R.mipmap.ic_score_checked);
                starTwo.setImageResource(R.mipmap.ic_score_checked);
                starThree.setImageResource(R.mipmap.ic_score_unchecked);
                starFour.setImageResource(R.mipmap.ic_score_unchecked);
                starFive.setImageResource(R.mipmap.ic_score_unchecked);
                mStarPriotity = 2;

            }
        });

        starThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageResource(R.mipmap.ic_score_checked);
                starTwo.setImageResource(R.mipmap.ic_score_checked);
                starThree.setImageResource(R.mipmap.ic_score_checked);
                starFour.setImageResource(R.mipmap.ic_score_unchecked);
                starFive.setImageResource(R.mipmap.ic_score_unchecked);
                mStarPriotity = 3;

            }
        });

        starFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageResource(R.mipmap.ic_score_checked);
                starTwo.setImageResource(R.mipmap.ic_score_checked);
                starThree.setImageResource(R.mipmap.ic_score_checked);
                starFour.setImageResource(R.mipmap.ic_score_checked);
                starFive.setImageResource(R.mipmap.ic_score_unchecked);
                mStarPriotity = 4;

            }
        });

        starFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starOne.setImageResource(R.mipmap.ic_score_checked);
                starTwo.setImageResource(R.mipmap.ic_score_checked);
                starThree.setImageResource(R.mipmap.ic_score_checked);
                starFour.setImageResource(R.mipmap.ic_score_checked);
                starFive.setImageResource(R.mipmap.ic_score_checked);
                mStarPriotity = 5;

            }
        });
    }

    private void setSunLight(){
        final ImageView sunOne = mSunContainer.findViewById(R.id.iv_sun_one);
        final ImageView sunTwo = mSunContainer.findViewById(R.id.iv_sun_two);
        final ImageView sunThree = mSunContainer.findViewById(R.id.iv_sun_three);
        final ImageView sunFour = mSunContainer.findViewById(R.id.iv_sun_four);
        final ImageView sunFive = mSunContainer.findViewById(R.id.iv_sun_five);

        sunOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunOne.setImageResource(R.mipmap.ic_sun_checked);
                sunTwo.setImageResource(R.mipmap.ic_sun_unchecked);
                sunThree.setImageResource(R.mipmap.ic_sun_unchecked);
                sunFour.setImageResource(R.mipmap.ic_sun_unchecked);
                sunFive.setImageResource(R.mipmap.ic_sun_unchecked);
                mSunPriotity = 1;
            }
        });
        sunTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunOne.setImageResource(R.mipmap.ic_sun_checked);
                sunTwo.setImageResource(R.mipmap.ic_sun_checked);
                sunThree.setImageResource(R.mipmap.ic_sun_unchecked);
                sunFour.setImageResource(R.mipmap.ic_sun_unchecked);
                sunFive.setImageResource(R.mipmap.ic_sun_unchecked);
                mSunPriotity = 2;

            }
        });

        sunThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunOne.setImageResource(R.mipmap.ic_sun_checked);
                sunTwo.setImageResource(R.mipmap.ic_sun_checked);
                sunThree.setImageResource(R.mipmap.ic_sun_checked);
                sunFour.setImageResource(R.mipmap.ic_sun_unchecked);
                sunFive.setImageResource(R.mipmap.ic_sun_unchecked);
                mSunPriotity = 3;

            }
        });

        sunFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunOne.setImageResource(R.mipmap.ic_sun_checked);
                sunTwo.setImageResource(R.mipmap.ic_sun_checked);
                sunThree.setImageResource(R.mipmap.ic_sun_checked);
                sunFour.setImageResource(R.mipmap.ic_sun_checked);
                sunFive.setImageResource(R.mipmap.ic_sun_unchecked);
                mSunPriotity = 4;

            }
        });

        sunFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunOne.setImageResource(R.mipmap.ic_sun_checked);
                sunTwo.setImageResource(R.mipmap.ic_sun_checked);
                sunThree.setImageResource(R.mipmap.ic_sun_checked);
                sunFour.setImageResource(R.mipmap.ic_sun_checked);
                sunFive.setImageResource(R.mipmap.ic_sun_checked);
                mSunPriotity = 5;

            }
        });
    }
}
