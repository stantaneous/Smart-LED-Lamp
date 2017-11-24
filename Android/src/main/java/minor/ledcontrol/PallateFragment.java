package minor.ledcontrol;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PallateFragment extends Fragment {

    private OnColorSelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnColorSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pallete, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.pallete);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ImageView img = (ImageView)view;
                final int evX = (int) motionEvent.getX();
                final int evY = (int) motionEvent.getY();
                img.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(img.getDrawingCache());
                img.setDrawingCacheEnabled(false);
                try {
                    int pxl = bitmap.getPixel(evX, evY);
                    mListener.OnColorSelected(pxl);
                } catch (Exception ignored){

                }
                bitmap.recycle();
                return true;
            }
        });
    }
}
