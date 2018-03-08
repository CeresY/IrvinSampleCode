package robot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
	最早的时候我是模拟PC端的鼠标点击
**/
public class TestRobot {
	public static void main(String[] args) {
		/*try {
			int x = 490, y = 390;
			Robot r = new Robot();
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON3_MASK);
			r.delay(10);
			r.mouseRelease(InputEvent.BUTTON3_MASK);
			r.delay(10);
//			Color c = r.getPixelColor(x, y);
			for(int i=0; i<20; i++) {
				r.keyPress(KeyEvent.VK_D);
				r.keyRelease(KeyEvent.VK_D);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		TestRobot f = new TestRobot();
		f.vote();
	}
	
	public void vote() {
		try {
			long b = System.currentTimeMillis();
			//Runtime.getRuntime().exec("iexplore.exe *32");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.mouseMove(100, 200);
			for(int i=0; i<1000; i++) {
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				robot.delay(5);
			}
			long e = System.currentTimeMillis();
			System.out.println((e-b)/1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void notepad() {
		try {
			Process process = Runtime.getRuntime()
			.exec("C://Soft//Notepad++//notepad++.exe");
			
			Robot robot = new Robot();
			robot.delay(2*1000);
			for(int i=0; i<10; i++) {
				pressKeyWithShit(robot, KeyEvent.VK_H);
				pressKey(robot, KeyEvent.VK_I);
				pressKey(robot, KeyEvent.VK_SPACE);
				pressKey(robot, KeyEvent.VK_I);
				pressKey(robot, KeyEvent.VK_SPACE);
				pressKey(robot, KeyEvent.VK_A);
				pressKey(robot, KeyEvent.VK_M);
				pressKey(robot, KeyEvent.VK_ENTER);
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pressKey(Robot robot, int keyValue) {
		robot.keyPress(keyValue);
		robot.keyRelease(keyValue);
	}
	
	public static void pressKeyWithShit(Robot robot, int keyValue) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(keyValue);
		robot.keyRelease(keyValue);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}
}

