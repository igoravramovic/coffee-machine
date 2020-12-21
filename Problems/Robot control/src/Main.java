class Move {
    public static void main(String[] args) {
        Robot robot = new Robot(0, 0, Direction.UP);
        moveRobot(robot, 0, -10);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        Direction currentDirection = robot.getDirection();
        if (robot.getX() > toX) {
            // Change direction of robot to left
            if (currentDirection.dx() == 1) {
                robot.turnLeft();
                robot.turnLeft();
            } else if (currentDirection.dx() == 0) {
                if (currentDirection.dy() == 1) {
                    robot.turnLeft();
                } else {
                    robot.turnRight();
                }
            }
            currentDirection = Direction.LEFT;

            // Move robot do destination X coordinate
            for (int i = robot.getX(); i > toX; i--) {
                robot.stepForward();
            }

            System.out.println(robot.getDirection());
            System.out.println(robot.getX());
        } else if (robot.getX() < toX) {

            // Change direction of robot to right
            if (currentDirection.dx() == -1) {
                robot.turnLeft();
                robot.turnLeft();
            } else if (currentDirection.dx() == 0) {
                if (currentDirection.dy() == 1) {
                    robot.turnRight();
                } else {
                    robot.turnLeft();
                }
            }
            currentDirection = Direction.RIGHT;
            System.out.println(robot.getDirection());
            // Move robot do destination X coordinate
            for (int i = robot.getX(); i < toX; i++) {
                robot.stepForward();
            }


            System.out.println(robot.getX());
        }

        if (robot.getY() > toY) {
            // Change direction of robot to left
            if (currentDirection == Direction.LEFT) {
                robot.turnLeft();
            } else if (currentDirection == Direction.RIGHT) {
                robot.turnRight();
            } else if (currentDirection == Direction.UP) {
                robot.turnLeft();
                robot.turnLeft();
            }
            System.out.println(robot.getDirection());
            // Move robot do destination X coordinate
            for (int i = robot.getY(); i > toY; i--) {
                robot.stepForward();
            }

            System.out.println(robot.getY());
        } else if (robot.getY() < toY) {

            // Change direction of robot to right
            if (currentDirection == Direction.LEFT) {
                robot.turnRight();
            } else if (currentDirection == Direction.RIGHT) {
                robot.turnLeft();
            } else if (currentDirection == Direction.DOWN) {
                robot.turnLeft();
                robot.turnLeft();
            }
            System.out.println(robot.getDirection());

            // Move robot do destination X coordinate
            for (int i = robot.getY(); i < toY; i++) {
                robot.stepForward();
            }

            System.out.println(robot.getY());
        }

        System.out.println(robot.getX() + " : " + robot.getY());
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}