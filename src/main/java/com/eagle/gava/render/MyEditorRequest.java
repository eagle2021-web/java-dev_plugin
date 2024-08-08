package com.eagle.gava.render;

public class MyEditorRequest implements EditorRequest{
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void cancel() {

    }
}
