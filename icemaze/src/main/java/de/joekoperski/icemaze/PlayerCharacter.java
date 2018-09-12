package de.joekoperski.icemaze;

import android.graphics.Point;
import android.graphics.Rect;

public class PlayerCharacter {
    private Point position;
    private Point previousPosition;
    private Direction impulse;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public PlayerCharacter(Point position) {
        this.position = new Point();
        previousPosition = new Point();

        this.position.x = position.x;
        this.position.y = position.y;
        previousPosition.x = position.x;
        previousPosition.y = position.y;
        impulse = Direction.STILL;
    }// PlayerCharacter


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Direction getImpulse() {
        return impulse;
    }// getImpulse


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setImpulse(Direction impulse) {
        this.impulse = impulse;
    }// setImpulse


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Point getPosition() {
        return position;
    }// getPosition


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPosition(Point position) {
        this.position = position;
    }// setPosition


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Point getPreviousPosition() {
        return previousPosition;
    }// getPreviousPosition


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void move(Rect bounds) {
        previousPosition.x = position.x;
        previousPosition.y = position.y;

        // TODO: 12.09.2018: Can this be done by getting the step from a table?
        switch (impulse) {
            case EAST:
                if (position.x < bounds.right) {
                    position.x += 1;
                }//if
                else {
                    impulse = Direction.STILL;
                }// else
                break;
            case WEST:
                if (position.x > bounds.left) {
                    position.x -= 1;
                }//if
                else {
                    impulse = Direction.STILL;
                }// else
                break;
            case NORTH:
                if (position.y > bounds.top) {
                    position.y -= 1;
                }//if
                else {
                    impulse = Direction.STILL;
                }// else
                break;
            case SOUTH:
                if (position.y < bounds.bottom) {
                    position.y += 1;
                }//if
                else {
                    impulse = Direction.STILL;
                }// else
                break;
            default:
        }// switch
    }
}
