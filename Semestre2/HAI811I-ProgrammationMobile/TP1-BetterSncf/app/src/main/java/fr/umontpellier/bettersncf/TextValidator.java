package fr.umontpellier.bettersncf;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public abstract class TextValidator implements TextWatcher
{
    public abstract void validate(String text);

    @Override
    final public void afterTextChanged(Editable editable)
    {
        validate(editable.toString());
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
