package com.tomsyb.lib_base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;
import com.tomsyb.lib_base.app.BaseApplication;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hewei on 2019/3/27 8:35.
 * <p>
 * 基于微信的开源Key-Value框架MMKV，不同于SharePreference文件读写，MMKV是直接内存读写，并可跨进程操作。{@link MMKV}
 */
public class MmkvUtil {

    private static final String MMKV_NAME = "PreferenceHold";

    private static MMKV getMmkv(String id, boolean multiProcess) {
        if (!multiProcess) {
            return MMKV.mmkvWithID(TextUtils.isEmpty(id) ? MMKV_NAME : id);
        } else {
            return MMKV.mmkvWithID(TextUtils.isEmpty(id) ? MMKV_NAME : id, MMKV.MULTI_PROCESS_MODE);
        }
    }

    private static boolean put(String key, Object value, MMKV mmkv) {
        if (value instanceof Boolean) {
            return mmkv.encode(key, (boolean) value);
        } else if (value instanceof Integer) {
            return mmkv.encode(key, (int) value);
        } else if (value instanceof Float) {
            return mmkv.encode(key, (float) value);
        } else if (value instanceof Double) {
            return mmkv.encode(key, (double) value);
        } else if (value instanceof Long) {
            return mmkv.encode(key, (long) value);
        } else if (value instanceof String) {
            return mmkv.encode(key, (String) value);
        } else if (value instanceof Set) {
            return mmkv.encode(key, (Set) value);
        } else if (value instanceof Parcelable) {
            return mmkv.encode(key, (Parcelable) value);
        }
        return false;
    }

    private static <T> T get(String key, T def, MMKV mmkv) {
        if (def instanceof Boolean) {
            return (T) ((Boolean) mmkv.decodeBool(key, (Boolean) def));
        } else if (def instanceof Integer) {
            return (T) ((Integer) mmkv.decodeInt(key, (Integer) def));
        } else if (def instanceof Float) {
            return (T) ((Float) mmkv.decodeFloat(key, (Float) def));
        } else if (def instanceof Double) {
            return (T) ((Double) mmkv.decodeDouble(key, (Double) def));
        } else if (def instanceof Long) {
            return (T) ((Long) mmkv.decodeLong(key, (Long) def));
        } else if (def instanceof String) {
            return (T) mmkv.decodeString(key, (String) def);
        } else if (def instanceof Set) {
            return (T) mmkv.decodeStringSet(key, (Set<String>) def);
        } else {
            return null;
        }
    }

    private static void remove(String id, boolean multiProcess, String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        MMKV mmkv = getMmkv(id, multiProcess);
        mmkv.removeValueForKey(key);
    }

    private static void remove(String id, boolean multiProcess, String[] keys) {
        if (keys.length == 0) {
            return;
        }
        MMKV mmkv = getMmkv(id, multiProcess);
        if (keys.length == 1) {
            mmkv.remove(keys[0]);
        } else {
            mmkv.removeValuesForKeys(keys);
            for (String key : keys) {
                mmkv.remove(key);
            }
        }
    }

    public static void initialize(Context context, boolean log) {
        MMKV.initialize(context);
        if (!log) {
            MMKV.setLogLevel(MMKVLogLevel.LevelNone);
        }
    }

    public static void importSharePreference(String id) {
        SharedPreferences sharedPreferences = BaseApplication.Companion.getApp().getSharedPreferences(MMKV_NAME, Context.MODE_PRIVATE);
        if (null != sharedPreferences.getAll() && !sharedPreferences.getAll().isEmpty()) {
            MMKV mmkv = MMKV.mmkvWithID(TextUtils.isEmpty(id) ? MMKV_NAME : id);
            mmkv.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().apply();
        }
    }

    public static boolean put(String key, Object value) {
        return put(null, key, value, false);
    }

    public static boolean put(String id, String key, Object value) {
        return put(id, key, value, false);
    }

    public static boolean put(String id, String key, Object value, boolean multiProcess) {
        return put(key, value, getMmkv(id, multiProcess));
    }

    public static boolean put(String key, byte[] bytes) {
        return put(null, key, bytes);
    }

    public static boolean put(String id, String key, byte[] bytes) {
        return put(id, key, bytes, false);
    }

    public static boolean put(String id, String key, byte[] bytes, boolean multiProcess) {
        MMKV mmkv = getMmkv(id, multiProcess);
        return mmkv.encode(key, bytes);
    }

    public static <T> T get(String key, T def) {
        return get(null, key, def, false);
    }

    public static <T> T get(String id, String key, T def) {
        return get(id, key, def, false);
    }

    public static <T> T get(String id, String key, T def, boolean multiProcess) {
        if (TextUtils.isEmpty(key) || null == def) {
            return def;
        }
        return get(key, def, getMmkv(id, multiProcess));
    }

    public static Parcelable getParcelable(String key, Class<? extends Parcelable> def) {
        return getParcelable(null, key, def);
    }

    public static Parcelable getParcelable(String id, String key, Class<? extends Parcelable> def) {
        return getParcelable(id, key, def, false);
    }

    public static Parcelable getParcelable(String id, String key, Class<? extends Parcelable> def, boolean multiProcess) {
        MMKV mmkv = getMmkv(id, multiProcess);
        return mmkv.decodeParcelable(key, def);
    }

    public static byte[] getByteArray(String id, String key) {
        return getByteArray(id, key, false);
    }

    public static byte[] getByteArray(String id, String key, boolean multiProcess) {
        MMKV mmkv = getMmkv(id, multiProcess);
        return mmkv.decodeBytes(key);
    }

    public static boolean containsKey(String key) {
        MMKV mmkv = getMmkv(null, false);
        return mmkv.containsKey(key);
    }

    public static boolean containsKey(String id, String key) {
        MMKV mmkv = getMmkv(id, false);
        return mmkv.containsKey(key);
    }

    public static boolean containsKey(String id, String key, boolean multiProcess) {
        MMKV mmkv = getMmkv(id, multiProcess);
        return mmkv.containsKey(key);
    }

    public static void removeValueForKey(String key) {
        remove(null, false, key);
    }

    public static void removeValueForKey(String id, List<String> keys) {
        if (null == keys || keys.isEmpty()) {
            return;
        }
        String[] array = new String[keys.size()];
        String[] array1 = keys.toArray(array);
        remove(id, false, array1);
    }

    public static void removeValueForKey(String id, boolean multiProcess, String... keys) {
        remove(id, multiProcess, keys);
    }

    public static void removeValueForKey(String id, boolean multiProcess, List<String> keys) {
        if (null == keys || keys.isEmpty()) {
            return;
        }
        String[] array = new String[keys.size()];
        String[] array1 = keys.toArray(array);
        remove(id, multiProcess, array1);
    }

    public static Map<String, ?> getAll(String id) {
        return getMmkv(id, false).getAll();
    }

    public static Map<String, ?> getAll(String id, boolean multiProcess) {
        return getMmkv(id, multiProcess).getAll();
    }


}
