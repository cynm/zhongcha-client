package com.zbar.lib.decode;

import android.os.Handler;
import android.os.Message;

import com.chengying.root.zhongcha.R;
import com.zbar.lib.CaptureActivity;
import com.zbar.lib.camera.CameraManager;


public final class CaptureActivityHandler extends Handler {

    DecodeThread decodeThread = null;
    CaptureActivity activity = null;
    private State state;


    public CaptureActivityHandler(CaptureActivity activity) {
        this.activity = activity;
        decodeThread = new DecodeThread(activity);
        decodeThread.start();
        state = State.SUCCESS;
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {

        switch (message.what) {
            case R.id.auto_focus:
                if (state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
                }
                break;
            case R.id.restart_preview:
                restartPreviewAndDecode();
                break;
            case R.id.decode_succeeded:
                state = State.SUCCESS;
                activity.handleDecode((String) message.obj);// 瑙ｆ瀽鎴愬姛锛屽洖璋�
                break;

            case R.id.decode_failed:
                state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(decodeThread.getHandler(),
                        R.id.decode);
                break;
        }

    }

    public void quitSynchronously() {
        state = State.DONE;
        CameraManager.get().stopPreview();
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
        removeMessages(R.id.decode);
        removeMessages(R.id.auto_focus);
    }

    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(),
                    R.id.decode);
            CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
        }
    }

    private enum State {
        PREVIEW, SUCCESS, DONE
    }

}
