package com.uoko.plugins.cmq.event;

/**
 * <p>
 *
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class CMQApplicationStatusChangedEvent extends CMQApplicationEvent {
    private static final long serialVersionUID = -6137805780577180089L;
    private final String mgs;

    public CMQApplicationStatusChangedEvent(String mgs) {
        super();
        this.mgs = mgs;
    }


    public String getMgs() {
        return mgs;
    }
}
