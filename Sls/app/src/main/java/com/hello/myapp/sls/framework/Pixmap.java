package com.hello.myapp.sls.framework;

/**
 * Created by Cloud on 2017-07-26.
 */
import com.hello.myapp.sls.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();// 넓이를 픽셀단위로 반환
    public int getHeight();// 높이를 픽셀단위로 반환
    public PixmapFormat getFormat();//램에 저장된 pixmap의 pixelformat을 반환
    public void dispose();// 이부분을 호출해ㅓ 사용했던 자원을 반환한다.

}
