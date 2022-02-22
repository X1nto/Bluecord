package mods.utils.translate;
public interface ITranslateCallback {
    void onError();

    void onResult(String str);
}
