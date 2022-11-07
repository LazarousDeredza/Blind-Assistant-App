package org.tensorflow.lite.examples.objectdetection;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\bH\u0016J\u0012\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0014\u0010\u0012\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activityMainBinding", "Lorg/tensorflow/lite/examples/objectdetection/databinding/ActivityMainBinding;", "textToSpeech", "Landroid/speech/tts/TextToSpeech;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "speakOut", "results", "", "Lorg/tensorflow/lite/task/vision/detector/Detection;", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.speech.tts.TextToSpeech textToSpeech;
    private org.tensorflow.lite.examples.objectdetection.databinding.ActivityMainBinding activityMainBinding;
    @org.jetbrains.annotations.NotNull()
    public static final org.tensorflow.lite.examples.objectdetection.MainActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String item = "empty";
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    public final void speakOut(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.tensorflow.lite.task.vision.detector.Detection> results) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lorg/tensorflow/lite/examples/objectdetection/MainActivity$Companion;", "", "()V", "item", "", "getItem", "()Ljava/lang/String;", "setItem", "(Ljava/lang/String;)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getItem() {
            return null;
        }
        
        public final void setItem(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
    }
}