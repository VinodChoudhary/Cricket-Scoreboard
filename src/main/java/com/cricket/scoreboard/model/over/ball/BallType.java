package main.java.com.cricket.scoreboard.model.over.ball;

public enum BallType {
    NORMAL {
        @Override
        public boolean isExtraBall() {
            return false;
        }
    }, WIDE {
        @Override
        public boolean isExtraBall() {
            return true;
        }
    }, NO_BALL {
        @Override
        public boolean isExtraBall() {
            return true;
        }
    }, WICKET {
        @Override
        public boolean isExtraBall() {
            return false;
        }
    };

    public abstract boolean isExtraBall();
}
