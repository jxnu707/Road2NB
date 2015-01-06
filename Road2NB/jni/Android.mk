LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := Road2NB
LOCAL_SRC_FILES := mJni_JniClient.c
include $(BUILD_SHARED_LIBRARY)