LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := DriveAssist
### Add all source file names to be included in lib separated by a whitespace
LOCAL_SRC_FILES := DriveAssist.cpp

include $(BUILD_SHARED_LIBRARY)
