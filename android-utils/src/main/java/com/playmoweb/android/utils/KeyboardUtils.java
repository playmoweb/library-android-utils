package com.playmoweb.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * KeyboardUtil
 *
 * @author Playmoweb
 */
public class KeyboardUtils {

    public static void closeKeyboard(final Activity activity) {
        if (activity != null) {
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }

            final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void showKeyboard(final Activity activity, final EditText editText) {
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void setListener(final View rootView, final KeyboardListener listener) {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private int viewHeight;
            private boolean opened = false;

            @Override
            public void onGlobalLayout() {
                final Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);

                final int viewHeight = r.bottom - r.top;

                if (this.viewHeight > (viewHeight + 100) && !opened) {
                    listener.onKeyboardOpened();
                    opened = true;
                } else if (this.viewHeight < (viewHeight - 100) && opened) {
                    listener.onKeyboardClosed();
                    opened = false;
                }
                this.viewHeight = viewHeight;
            }
        });

    }

    public interface KeyboardListener {
        void onKeyboardOpened();

        void onKeyboardClosed();
    }
}
