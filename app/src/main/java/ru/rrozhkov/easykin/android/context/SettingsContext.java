package ru.rrozhkov.easykin.android.context;

/**
 * Created by rrozhkov on 3/17/2017.
 */

public class SettingsContext {
    private static SettingsContext context = null;
    private boolean showClosedTask = false;

    public static SettingsContext instance(){
        if(context==null){
            context = new SettingsContext();
        }
        return context;
    }

    private  SettingsContext() {
    }

    public boolean isShowClosedTask() {
        return showClosedTask;
    }

    public void setShowClosedTask(boolean showClosedTask) {
        this.showClosedTask = showClosedTask;
    }
}
