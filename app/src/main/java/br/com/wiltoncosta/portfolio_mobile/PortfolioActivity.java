package br.com.wiltoncosta.portfolio_mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.wiltoncosta.portfolio_mobile.model.Profile;
import br.com.wiltoncosta.portfolio_mobile.task.ProfileTask;

public class PortfolioActivity extends AppCompatActivity {

    private PortfolioHelper helper;

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        helper = new PortfolioHelper(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tx = fragmentManager.beginTransaction();

            tx.replace(R.id.contentPlaceHolder, new AboutMeFragment(), "aboutFragment");
            tx.commit();
        }

        //Gets profile from WebAPI and updates the screen
        ProfileTask task = new ProfileTask(this);
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_portfolio, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLinkedIn:
                Intent intentLinkedIn = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/wilton-gomes-da-costa-j%C3%BAnior-76334b91/?locale=en_US"));
                startActivity(intentLinkedIn);
                break;
            case R.id.menuGithub:
                Intent intentGithub = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/wiltonABC"));
                startActivity(intentGithub);
                break;
            case R.id.menuAboutMe:
                helper.replaceFragment(new AboutMeFragment(), "aboutFragment");
                break;
            case R.id.menuSkills:
                helper.replaceFragment(new SkillsFragment(), "skillsFragment");
                break;

            case R.id.menuWork:
                helper.replaceFragment(new WorkFragment(), "workFragment");
                break;
            case R.id.menuContact:
                helper.replaceFragment(new ContactFragment(), "contactFragment");
                break;
            case R.id.menuFeedbacks:
                helper.replaceFragment(new FeedbackFragment(), "feedbackFragment");
                break;
            case R.id.menuAboutApp:
                helper.replaceFragment(new AboutAppFragment(), "aboutAppFragment");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public PortfolioHelper getHelper() {
        return this.helper;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }

}
