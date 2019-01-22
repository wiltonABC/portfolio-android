package br.com.wiltoncosta.portfolio_mobile.helper;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.Date;

import br.com.wiltoncosta.portfolio_mobile.PortfolioActivity;
import br.com.wiltoncosta.portfolio_mobile.R;
import br.com.wiltoncosta.portfolio_mobile.model.Message;
import br.com.wiltoncosta.portfolio_mobile.task.MessageTask;
import br.com.wiltoncosta.portfolio_mobile.validator.RequiredValidator;
import br.com.wiltoncosta.portfolio_mobile.validator.Validator;

public class ContactHelper {

    private EditText name;
    private EditText email;
    private EditText subject;
    private EditText message;
    private Button button;

    private Context context;

    public ContactHelper(final Context context, View view) {
        this.name = view.findViewById(R.id.contactName);
        final RequiredValidator nameValidator = new RequiredValidator(name,
                context.getString(R.string.fillIn,context.getString(R.string.name)));
        this.email = view.findViewById(R.id.contactEmail);
        final RequiredValidator emailValidator = new RequiredValidator(email,
                context.getString(R.string.fillIn,context.getString(R.string.email)));
        this.subject = view.findViewById(R.id.contactSubject);
        final RequiredValidator subjectValidator = new RequiredValidator(subject,
                context.getString(R.string.fillIn,context.getString(R.string.subject)));
        this.message = view.findViewById(R.id.contactMessage);
        final RequiredValidator messageValidator = new RequiredValidator(message,
                context.getString(R.string.fillIn,context.getString(R.string.message)));

        this.button = view.findViewById(R.id.contactButton);

        this.context = context;

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate fields
                nameValidator.validate(name.getText().toString());
                emailValidator.validate(email.getText().toString());
                subjectValidator.validate(subject.getText().toString());
                messageValidator.validate(message.getText().toString());

                if (!nameValidator.isValid() || !emailValidator.isValid()
                        || !subjectValidator.isValid() || !messageValidator.isValid())
                    return;

                //Call HttpRequest Task
                MessageTask messageTask = new MessageTask(context);
                messageTask.execute(getMessageFromForm());

                clearForm(nameValidator, emailValidator, subjectValidator, messageValidator);
            }
        });
    }

    private Message getMessageFromForm() {
        Message message = new Message();
        message.setName(this.name.getText().toString());
        message.setEmail(this.email.getText().toString());
        message.setSubject(this.subject.getText().toString());
        message.setMessage(this.message.getText().toString());
        message.setDateCreated(new Date());

        message.setProfile(((PortfolioActivity)this.context).getProfile());

        return message;
    }

    private void clearForm(Validator... validators) {
        this.name.setText("");
        this.email.setText("");
        this.subject.setText("");
        this.message.setText("");

        //Clear validators messages
        for (Validator validator:validators) {
            validator.clearValidationError();
        }

        this.name.requestFocus();
    }

}
