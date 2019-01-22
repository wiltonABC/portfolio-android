package br.com.wiltoncosta.portfolio_mobile.helper;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Date;

import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Feedback;
import br.com.wiltoncosta.portfolio_mobile.task.FeedbackListTask;
import br.com.wiltoncosta.portfolio_mobile.task.FeedbackTask;
import br.com.wiltoncosta.portfolio_mobile.validator.RequiredValidator;
import br.com.wiltoncosta.portfolio_mobile.validator.Validator;

public class FeedbackHelper {

    private EditText author;
    private EditText company;
    private EditText message;
    private Button button;
    private RelativeLayout feedbacksListLayout;
    private ImageView imageShowFeedbacks;

    private Context context;

    public FeedbackHelper(final Context context, View view) {
        this.author = view.findViewById(R.id.feedbackAuthor);
        final RequiredValidator authorValidator = new RequiredValidator(author,
                context.getString(R.string.fillIn,context.getString(R.string.author)));
        this.company = view.findViewById(R.id.feedbackCompany);
        final RequiredValidator companyValidator = new RequiredValidator(company,
                context.getString(R.string.fillIn,context.getString(R.string.company)));
        this.message = view.findViewById(R.id.feedbackMessage);
        final RequiredValidator messageValidator = new RequiredValidator(message,
                context.getString(R.string.fillIn,context.getString(R.string.feedback)));

        this.button = view.findViewById(R.id.feedbackButton);

        this.imageShowFeedbacks = view.findViewById(R.id.imageButtonShow);

        this.context = context;

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate fields
                authorValidator.validate(author.getText().toString());
                companyValidator.validate(company.getText().toString());
                messageValidator.validate(message.getText().toString());

                if (!authorValidator.isValid() || !companyValidator.isValid()
                        || !messageValidator.isValid())
                    return;

                //Call HttpRequest Task
                FeedbackTask feedbackTask = new FeedbackTask(context);
                feedbackTask.execute(getFeedbackFromForm());

                clearForm(authorValidator, companyValidator, messageValidator);

                FeedbackListTask feedbackListTask = new FeedbackListTask(context);
                feedbackListTask.execute();
            }
        });

        feedbacksListLayout = view.findViewById(R.id.feedbackListLayout);
        final RelativeLayout parent = (RelativeLayout) feedbacksListLayout.getParent();

        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                feedbacksListLayout.setTranslationX(parent.getWidth() - 75);
            }
        });

        imageShowFeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedbacksListLayout.getTranslationX() == 0) {
                    feedbacksListLayout.animate().translationX(parent.getWidth() - 75);
                } else {
                    feedbacksListLayout.animate().translationX(0);
                }

            }
        });
    }

    private Feedback getFeedbackFromForm() {
        Feedback feedback = new Feedback();
        feedback.setAuthor(this.author.getText().toString());
        feedback.setCompany(this.company.getText().toString());
        feedback.setText(this.message.getText().toString());
        feedback.setDateCreated(new Date());

        feedback.setProfile(((PortfolioActivity)this.context).getProfile());

        return feedback;
    }

    private void clearForm(Validator... validators) {
        this.author.setText("");
        this.company.setText("");
        this.message.setText("");

        //Clear validators messages
        for (Validator validator:validators) {
            validator.clearValidationError();
        }

        this.author.requestFocus();
    }

}
