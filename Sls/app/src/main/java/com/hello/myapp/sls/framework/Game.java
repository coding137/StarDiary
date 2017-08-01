package com.hello.myapp.sls.framework;

/**
 * Created by Cloud on 2017-07-26.
 * 인스턴스를 생성하고 관리하게될 인터페이스이다.
 * 창과 ui 컴포넌트를 설정하고 창 및 입력이벤트를 받을 수 있게끔 콜백을 연결한다.
 * 메인 루프 스레드를 시작한다.
 * 현재 화면을 추적하고 매번 메인루프가 반복될 때마다 (매 프레임마다) 화면을 업데이트해 보여주도록 명령한다.
 * 창이벤트(일시정지등)를 ui 스레드로부터 메인루프 스레드로 전달하고 이를 다시 현재 화면으로 전달해 이에 따라 상태가 바뀌게끔한다.
 * 지금까지 개발한 모든 모듈이 접근할수 있게해준다.
 *
 */

public interface Game {
    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();
    public Audio getAudio();
    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getStartScreen();

}
