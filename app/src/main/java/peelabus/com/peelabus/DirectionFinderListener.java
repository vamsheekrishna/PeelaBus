package peelabus.com.peelabus;

import java.util.List;

/**
 * Created by kiriti_sai on 09/12/17.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
