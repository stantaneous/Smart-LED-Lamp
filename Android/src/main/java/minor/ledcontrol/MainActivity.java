package minor.ledcontrol;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnColorSelectedListener  {

    public boolean isLedOn = false;
    public boolean isSliderView = true;
    private  WebView webView;
    private  View bulb;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        bulb = findViewById(R.id.color_view);
        textView = (TextView) findViewById(R.id.textView);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, new SlidersFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_led_switch,menu);
        final MenuItem switchled = menu.findItem(R.id.menu_switchled);
        final MenuItem switchlayout = menu.findItem(R.id.menu_switchlayout);
        switchled.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.menu_switchled){
                    if (isLedOn){
                        OnColorSelected(Color.rgb(0,0,0));
                        switchled.setIcon(R.drawable.ic_led_off);
                        isLedOn = false;
                    } else {
                        OnColorSelected(Color.rgb(240,220,10));
                        switchled.setIcon(R.drawable.ic_led_on);
                        isLedOn = true;
                    }
                }
                return true;
            }
        });
        switchlayout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.menu_switchlayout){
                    if (isSliderView){
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_layout, new PallateFragment())
                                .commit();
                        switchlayout.setIcon(R.drawable.ic_pallete);
                        isSliderView = false;

                    } else {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_layout, new SlidersFragment())
                                .commit();
                        switchlayout.setIcon(R.drawable.ic_sliders);
                        isSliderView = true;
                    }
                }
                return true;
            }
        });
        return true;
    }


    @Override
    public void OnColorSelected(int color) {
        bulb.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        textView.setText(String.format("%s %s", getString(R.string.color), String.format("#%06X", (0XFFFFFF & color))));
        webView.loadUrl("http://"+getString(R.string.ip_address)+":"+getString(R.string.port_number)+"/?Color="+color+";");
    }
}
