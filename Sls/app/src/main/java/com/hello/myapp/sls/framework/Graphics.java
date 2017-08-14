package com.hello.myapp.sls.framework;

/**
 * Created by Cloud on 2017-07-25.

   1. 디스크로부터 이미지를 로드해  나중에 그릴 수 잇게 메모리에 저장한다.
   2. 지난 번 프레임으로부터 남은 내용을 지울 수 있게 색상을 사용해 프레임버퍼를 정리한다.
   3. 특정 위치의 프레임 버퍼 픽셀을 특정색상으로 설정한다.
   4. 프레임버퍼에 선과 사각형을 그린다.
   5. 앞에서 로드한 이미지를 프레임버퍼에 그린다. 이때 전체 이미지를 그리거나 이미지의 일부영역을 그린다. 아울러 블렌딩을 활성화 또는 비활성화해 이미지를 그릴 수 있어야한다.
    프레임 버퍼의 크기를 알아온다.


 */

public interface Graphics {
    public  static enum PixmapFormat{

        ARGB8888,ARGB4444,RGB565
    }
    public Pixmap newPixmap(String fileName, PixmapFormat format);//jpeg or png 형식으로 된 이미지를 로드한다. 여기서는 로딩 매커니즘의 힌트가 되는 pixmap 결과에 대해 원하는 형식을 지정한다. RGB888등.

    public void clear(int color);// 지정된 칼라로 초기화를한다.

    public void drawPixel(int x , int y, int color);// 프레임 버퍼의 주저인 color로 설정한다.
    public void drawLine(int x, int y, int x2, int y2, int color);// 선의 시작점과 끝점의 color를 지정한다.
    public void drawRect(int x, int y, int width, int height, int color);//프레임버퍼의 사각형을 그린다.
    public void drawPixmap(Pixmap pixmap,int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);//
}
