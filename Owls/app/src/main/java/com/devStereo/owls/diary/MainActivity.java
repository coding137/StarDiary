package com.devStereo.owls.diary;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.devStereo.owls.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MainActivity extends AppCompatActivity{

    //슬라이드 열기/닫기 플래그
    boolean isPageOpen = false;
    //슬라이드 열기 애니메이션
    Animation translateLeftAnim;
    //슬라이드 닫기 애니메이션
    Animation translateRightAnim;
    //슬라이드 레이아웃
    LinearLayout slidingPage01;

    ImageButton button1;
    ImageButton button_profile;
    ImageButton button_matching;
    ImageButton button_list;

    ImageButton[] sButton = new ImageButton[20];
    public static SQLiteHelper sqLiteHelper;
    ImageButton button_write;

    public  static Context mContext;

    private int star=0;
    private int starrandom=0;
    private int[] starArrary1={18,13,14,17,9,6,15,12,8,19,7,0,16,2,11,3,5,4,1,10};
    private int[] starArrary2={14,13,18,7,19,8,12,15,6,9,17,10,1,4,5,3,11,2,16,0};
    private int[] starArrary3={13,14,18,15,12,8,19,7,6,9,17,3,5,4,1,10,11,2,16,0};

    LinearLayout sky;
    LinearLayout sidebar;
    LinearLayout slidingPage;

public  BundleManager bundleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext= this;// 다른위치에서 함수를 부르기위해서 선언
        sqLiteHelper= new SQLiteHelper(this,"MemoDB.sqlite", null,1);

        sqLiteHelper.queryDate("CREATE TABLE IF NOT EXISTS  MEMO (Tid INTEGER PRIMARY KEY AUTOINCREMENT,topic varchar, contents varchar)");
        bundleManager = new BundleManager();

        Toast.makeText(getApplicationContext(),"mainActivity oncreate",Toast.LENGTH_SHORT);
        sButton[0] = (ImageButton) findViewById(R.id.star1);
        sButton[1] = (ImageButton) findViewById(R.id.star2);
        sButton[2] = (ImageButton) findViewById(R.id.star3);
        sButton[3] = (ImageButton) findViewById(R.id.star4);
        sButton[4] = (ImageButton) findViewById(R.id.star5);
        sButton[5] = (ImageButton) findViewById(R.id.star6);
        sButton[6] = (ImageButton) findViewById(R.id.star7);
        sButton[7] = (ImageButton) findViewById(R.id.star8);
        sButton[8] = (ImageButton) findViewById(R.id.star9);
        sButton[9] = (ImageButton) findViewById(R.id.star10);
        sButton[10] = (ImageButton) findViewById(R.id.star11);
        sButton[11] = (ImageButton) findViewById(R.id.star12);
        sButton[12] = (ImageButton) findViewById(R.id.star13);
        sButton[13] = (ImageButton) findViewById(R.id.star14);
        sButton[14] = (ImageButton) findViewById(R.id.star15);
        sButton[15] = (ImageButton) findViewById(R.id.star16);
        sButton[16] = (ImageButton) findViewById(R.id.star17);
        sButton[17] = (ImageButton) findViewById(R.id.star18);
        sButton[18] = (ImageButton) findViewById(R.id.star19);
        sButton[19] = (ImageButton) findViewById(R.id.star20);

        starrandom = (int)(Math.random()*2);

        sky = (LinearLayout) findViewById(R.id.sky);
        sidebar = (LinearLayout) findViewById(R.id.sidebar);
        slidingPage = (LinearLayout) findViewById(R.id.slidingPage01);

        //UI
        slidingPage01 = (LinearLayout)findViewById(R.id.slidingPage01);
        button1 = (ImageButton)findViewById(R.id.buton1);
        button_profile = (ImageButton)findViewById(R.id.profile);
        button_matching = (ImageButton)findViewById(R.id.matching);
        button_list = (ImageButton)findViewById(R.id.list);
        button_write = (ImageButton)findViewById(R.id.writeButton);

        //애니메이션
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.tran_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.tran_right);

        //애니메이션 리스너 설정
        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

        //Drawable starAlpha = sButton[0].getBackground();
        //alpha1.setAlpha(0);
        /*
        Drawable[] starAlpha = new Drawable[20];
        for(int i=0; i<20;i++){
            starAlpha[i] = sButton[i].getBackground();
            starAlpha[i].setAlpha(0);
        }
        */
        sky.setVisibility(View.VISIBLE);
        sidebar.setVisibility(View.VISIBLE);
        //for(int i=star; i<20;i++) {
            //sButton[i].setVisibility(View.GONE);
        //}
        //for(int i=0; i<star;i++) {
           // sButton[i].setVisibility(View.VISIBLE);
        //}
        /*
            layout.setVisibility(View.VISIBLE);
            해당 뷰를 보여줌

            layout.setVisibility(View.INVISIBLE);
            해당 뷰를 안 보여줌(공간은 존재)

            layout.setVisibility(View.GONE);
            해당 뷰를 안 보여줌(공간마저 감춤)
         */

    }
    //onButtonprofile
    //onButtonmatching
    //onButtonlist
    //버튼
    public void onButton1Clicked(View v){
        //닫기
        if(isPageOpen){
            //애니메이션 시작
            slidingPage01.startAnimation(translateRightAnim);
        }
        //열기
        else{
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnim);
        }
    }

    public void onButtonprofile(View v){
        sky.setVisibility(View.GONE);
        sidebar.setVisibility(View.GONE);
        button_write.setVisibility(View.GONE);
        onButton1Clicked(v);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new ProfileFragment());
        fragmentTransaction.commit();

    }
    public void onButtonmatching(View v){
        sky.setVisibility(View.GONE);
        sidebar.setVisibility(View.GONE);
        button_write.setVisibility(View.GONE);
        onButton1Clicked(v);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new MatchingFragment());
        fragmentTransaction.commit();

    }
    public void onButtonlist(View v){
        sky.setVisibility(View.GONE);
        sidebar.setVisibility(View.GONE);
        button_write.setVisibility(View.GONE);
        onButton1Clicked(v);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new DiarylistFragment());
        fragmentTransaction.commit();
    }

    public void gowrite(View v){
        sky.setVisibility(View.GONE);
        sidebar.setVisibility(View.GONE);
        button_write.setVisibility(View.GONE);
        if(isPageOpen)
            onButton1Clicked(v);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new WriteFragment());
        fragmentTransaction.commit();


    }

    public void goFix(View v){

        sky.setVisibility(View.GONE);
        sidebar.setVisibility(View.GONE);
        button_write.setVisibility(View.GONE);
        if(isPageOpen)
            onButton1Clicked(v);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new FixFragment());
        fragmentTransaction.commit();


    }

    public void createStar(View v){
        //sButton[star].setVisibility(View.VISIBLE);
        if(starrandom == 0)
            sButton[starArrary1[star]].setImageResource(R.drawable.starlite);
        else if(starrandom == 1)
            sButton[starArrary2[star]].setImageResource(R.drawable.starlite);
        else
            sButton[starArrary3[star]].setImageResource(R.drawable.starlite);
        if(star<19)
            star++;
        goback(v);
    }

    public void goback(View v){
        sky.setVisibility(View.VISIBLE);
        sidebar.setVisibility(View.VISIBLE);
        button_write.setVisibility(View.VISIBLE);

        if(isPageOpen)
            onButton1Clicked(v);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new BlankFragment());
        fragmentTransaction.commit();
    }


    //애니메이션 리스너
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            //슬라이드 열기->닫기
            if(isPageOpen){
                slidingPage01.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            }
            //슬라이드 닫기->열기
            else{
                isPageOpen = true;
            }
        }
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
        @Override
        public void onAnimationStart(Animation animation) {

        }
    }
}

