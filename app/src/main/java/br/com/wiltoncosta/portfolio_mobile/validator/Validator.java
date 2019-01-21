package br.com.wiltoncosta.portfolio_mobile.validator;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.wiltoncosta.portfolio_mobile.R;


public abstract class Validator {
    protected static int viewId = 0;

    protected EditText fieldToValidate;
    protected String errorMessage;
    protected boolean valid = false;

    protected Validator(EditText fieldToValidate, String errorMessage) {

        this.fieldToValidate = fieldToValidate;
        this.errorMessage = errorMessage;
    }

    protected void showValidationError() {

        RelativeLayout parent = (RelativeLayout) fieldToValidate.getParent();

        fieldToValidate.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(fieldToValidate.getContext(), R.drawable.ic_error_red_24dp), null);

        ImageView imageView = getArrowImageView();

        TextView textView = getErrorTextView(imageView);

        parent.addView(imageView, parent.indexOfChild(fieldToValidate) + 1);
        parent.addView(textView, parent.indexOfChild(fieldToValidate) + 2);

        //Add eventListener to validate when the user fills in the field
        fieldToValidate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validate(fieldToValidate.getText().toString());
            }
        });

    }

    protected TextView getErrorTextView(ImageView arrowImageView) {
        TextView textView = new TextView(fieldToValidate.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(fieldToValidate.getLayoutParams());
        params.addRule(RelativeLayout.BELOW,arrowImageView.getId());
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.topMargin = -dpToPixels(10);
        params.rightMargin = ((RelativeLayout.LayoutParams)fieldToValidate.getLayoutParams()).rightMargin + dpToPixels(5);
        textView.setPadding(dpToPixels(10), dpToPixels(5), dpToPixels(10), dpToPixels(5));
        textView.setTextColor(Color.rgb(199,199,199));
        textView.setLayoutParams(params);
        textView.setText(errorMessage);
        textView.setTag(fieldToValidate.getId());
        textView.setBackground(ContextCompat.getDrawable(fieldToValidate.getContext(), R.drawable.validator_background));
        return textView;
    }


    protected ImageView getArrowImageView() {

        ImageView imageView = new ImageView(fieldToValidate.getContext());
        RelativeLayout.LayoutParams paramsImage = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsImage.addRule(RelativeLayout.BELOW,fieldToValidate.getId());
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsImage.rightMargin = ((RelativeLayout.LayoutParams)fieldToValidate.getLayoutParams()).rightMargin + dpToPixels(5);

        paramsImage.topMargin = -(((RelativeLayout.LayoutParams)fieldToValidate.getLayoutParams()).bottomMargin
                + dpToPixels(15));
        imageView.setLayoutParams(paramsImage);
        imageView.setImageDrawable(ContextCompat.getDrawable(fieldToValidate.getContext(), R.drawable.ic_arrow_drop_up_red_24dp));
        imageView.setTag("imageView" + fieldToValidate.getId());
        int id = ++viewId;
        imageView.setId(id);

        return imageView;
    }

    public void clearValidationError() {

        RelativeLayout parent = (RelativeLayout) fieldToValidate.getParent();

        fieldToValidate.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

        TextView textView = parent.findViewWithTag(fieldToValidate.getId());

        ImageView imageView = parent.findViewWithTag("imageView" + fieldToValidate.getId());

        if (textView != null)
            parent.removeView(textView);

        if (imageView != null) {
            parent.removeView(imageView);
        }
    }

    public abstract void validate(String value);

    public boolean isValid() {
        return valid;
    }

    protected int dpToPixels(int dpValue) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, fieldToValidate.getContext().getResources().getDisplayMetrics());
    }

}
