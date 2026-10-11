/*
 * Copyright (C) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.commands.monkey;

import android.app.IActivityManager;
import android.view.IWindowManager;
/**
 * monkey screen rotation event
 */
public class MonkeyRotationEvent extends MonkeyEvent {

    private final int mRotationDegree;
    private final boolean mPersist;

    /**
     * Construct a rotation Event.
     *
     * @param degree Possible rotation degrees, see constants in
     * anroid.view.Suface.
     * @param persist Should we keep the rotation lock after the orientation
     * change.
     */
    public MonkeyRotationEvent(int degree, boolean persist) {
        super(EVENT_TYPE_ROTATION);
        mRotationDegree = degree;
        mPersist = persist;
    }

    @Override
    public int injectEvent(IWindowManager iwm, IActivityManager iam, int verbose) {
        if (verbose > 0) {
            Logger.out.println(":Sending rotation degree=" + mRotationDegree +
                               ", persist=" + mPersist);
        }

        // 反射调用 freezeRotation/thawRotation，兼容不同 Android 版本（新版本已移除这些方法）
        try {
            java.lang.reflect.Method freeze = iwm.getClass().getMethod("freezeRotation", int.class);
            freeze.invoke(iwm, mRotationDegree);
            if (!mPersist) {
                java.lang.reflect.Method thaw = iwm.getClass().getMethod("thawRotation");
                thaw.invoke(iwm);
            }
            return MonkeyEvent.INJECT_SUCCESS;
        } catch (NoSuchMethodException e) {
            // 新 Android 无 freezeRotation，跳过旋转恢复
            return MonkeyEvent.INJECT_SUCCESS;
        } catch (Throwable ex) {
            return MonkeyEvent.INJECT_ERROR_REMOTE_EXCEPTION;
        }
    }
}
