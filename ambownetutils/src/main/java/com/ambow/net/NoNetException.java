package com.ambow.net;


import com.ambow.ambownetutils.R;
import com.ambow.base.NetApp;

public class NoNetException extends RuntimeException {
    public NoNetException() {
        super(NetApp.getAppContext().getString(R.string.check_your_network_connection));
    }
    public String toString() {
        return NetApp.getAppContext().getString(R.string.check_your_network_connection);
    }

}
