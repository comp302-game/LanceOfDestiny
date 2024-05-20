package tr.edu.ku.Domain.Spell;

import tr.edu.ku.Domain.Ymir.DoubleAccel;
import tr.edu.ku.Domain.Ymir.InfiniteVoid;

public class SpellController {

    //singleton pattern for spell control mechanism
    private static SpellController instance;
    private final Object spellLock = new Object(); // Lock object for synchronization

    //NORMAL SPELLS
    private boolean HEX_Active;
    private boolean OFB_Active;
    private boolean MSE_Active;

    //YMIR SPELLS
    private boolean VOID_Active;
    private boolean ACCEL_Active;
    

    private Hex current_HEX;
    private EnlargementSpell current_OFB;
    private StaffExpansion current_MSE;

    private InfiniteVoid current_VOID;
    private DoubleAccel current_ACCEL;


    //singleton pattern (only one instance)
    public static SpellController getInstance() {
		if (instance == null) {
			instance = new SpellController();
		}
		return instance;
	}


    public void reset() {
        HEX_Active = false;
        OFB_Active = false;
        MSE_Active = false;
        current_HEX= null;
    } 






//GETTER SETTER

    public Object getLock() {
        return spellLock;
    }


    public boolean is_HEX_Active() {
        return HEX_Active;
    }

    public boolean is_OFB_Active() {
        return OFB_Active;
    }

    public boolean is_MSE_Active() {
        return MSE_Active;
    }
    


    public void setHEX(boolean b) {
        HEX_Active = b;
    }

    public void setOFB(boolean b) {
        OFB_Active = b;
    }

    public void setMSE(boolean b) {
        MSE_Active = b;
    }


    public boolean is_VOID_Active() {
        return VOID_Active;
    }

    public boolean is_ACCEL_Active() {
        return ACCEL_Active;
    }
    

    public void setVOID(boolean b) {
        VOID_Active = b;
    }


    public void setACCEL(boolean b) {
        ACCEL_Active = b;
    }


    public Hex getCurrentHEX() {
        return current_HEX;
    }

    public void setCurrentHEX(Hex spell) {
        current_HEX = spell;
    }

    public EnlargementSpell getCurrentOFB() {
        return current_OFB;
    }

    public void setCurrentOFB(EnlargementSpell spell) {
        current_OFB = spell;
    }

    public StaffExpansion getCurrentMSE() {
        return current_MSE;
    }

    public void setCurrentMSE(StaffExpansion spell) {
        current_MSE = spell;
    }


    public InfiniteVoid getCurrentVOID() {
        return current_VOID;
    }

    public void setCurrentVOID(InfiniteVoid spell) {
        current_VOID = spell;
    }

    public DoubleAccel getCurrentACCEL() {
        return current_ACCEL;
    }

    public void setCurrentACCEL(DoubleAccel spell) {
        current_ACCEL = spell;
    }
}
