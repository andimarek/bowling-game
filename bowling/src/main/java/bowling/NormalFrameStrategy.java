package bowling;

/**
 * The normal frame strategy implementation: A bonus for a strike or spare. A Frame is finished after max two Rolls.
 */
public class NormalFrameStrategy implements FrameStrategy{

    private static final int BONUS_FOR_SPARE = 1;
    private static final int BONUS_FOR_STRIKE = 2;

    private static final int MAX_FRAME_SCORE = 10;

    @Override
    public void newRollAdded( Frame frame, int rollValue, RollListenerManager rollListenerManager ){
        frame.addScore( rollValue );

        if( isStrike( frame ) ){
            rollListenerManager.addNewListener( new BonusListener( BONUS_FOR_STRIKE, frame, rollListenerManager ) );
        }

        if( isSpare( frame ) ){
            rollListenerManager.addNewListener( new BonusListener( BONUS_FOR_SPARE, frame, rollListenerManager ) );
        }
    }

    private boolean isStrike( Frame frame ){
        return frame.getRollCount() == 1 && frame.getScore() == MAX_FRAME_SCORE;
    }

    private boolean isSpare( Frame frame ){
        return frame.getRollCount() == 2 && frame.getScore() == MAX_FRAME_SCORE;
    }

    @Override
    public boolean isFinished( Frame frame ){
        return isStrike( frame ) ? true : frame.getRollCount() == 2;
    }

    /**
     * Listens for new Rolls and adds the rollValue to the score as bonus.
     */
    public static class BonusListener implements RollListener{

        // How many times a bonus should be added: 2 for strike, 1 for spare
        int bonusCount;
        Frame frame;
        RollListenerManager rollListenerManager;

        public BonusListener( int bonusCount, Frame frame, RollListenerManager rollListenerManager ){
            super();
            this.bonusCount = bonusCount;
            this.frame = frame;
            this.rollListenerManager = rollListenerManager;
        }

        @Override
        public void newRoll( int rollValue ){
            frame.addScore( rollValue );
            if( --bonusCount == 0 ){
                rollListenerManager.removeListener( this );
            }
        }

    }

}
