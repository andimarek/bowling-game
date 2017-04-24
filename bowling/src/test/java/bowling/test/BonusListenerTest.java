package bowling.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import bowling.Frame;
import bowling.NormalFrameStrategy.BonusListener;
import bowling.RollListenerManager;

public class BonusListenerTest{

    private RollListenerManager rollListenerManager;
    private Frame frame;

    @Before
    public void setup(){
        rollListenerManager = mock( RollListenerManager.class );
        frame = mock( Frame.class );
    }
    
    @Test
    public void addScoreShouldBeCalled(){
        int bonusCount = 2;
        BonusListener bonusListener = new BonusListener( bonusCount, frame, rollListenerManager );
        
        int rollValue = 5;
        bonusListener.newRoll( rollValue );
        
        verify( frame ).addScore( rollValue );
    }
    
    @Test
    public void shouldRemoveListenersWhenFinished(){
        int bonusCount = 2;
        BonusListener bonusListener = new BonusListener( bonusCount, frame, rollListenerManager );
        
        int rollValue1 = 5;
        bonusListener.newRoll( rollValue1 );
        int rollValue2 = 2;
        bonusListener.newRoll( rollValue2 );
        
        verify( rollListenerManager ).removeListener( bonusListener );
    }

}
