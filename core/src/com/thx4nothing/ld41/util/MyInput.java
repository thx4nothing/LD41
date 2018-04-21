package com.thx4nothing.ld41.util;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;

public class MyInput extends InputAdapter {

	private class KeyState {
		public boolean isPressed = false;
		public boolean isJustReleased = false;
	}

	private static ArrayMap<Integer, KeyState> keyStates = new ArrayMap<Integer, KeyState>();
	public static Vector2 mouse = new Vector2();
	public static boolean mouseLeftDown = false;
	public static boolean mouseRightDown = false;

	public MyInput(int... keys) {
		for (int key : keys) {
			keyStates.put(key, new KeyState());
		}
	}

	public static boolean isKeyDown(int key) {
		if (keyStates.containsKey(key)) {
			return keyStates.get(key).isPressed;
		}
		return false;
	}

	public static boolean isKeyJustReleased(int key) {
		if (keyStates.containsKey(key)) {
			return keyStates.get(key).isJustReleased;
		}
		return false;
	}

	public static void clear() {
		for (int key : keyStates.keys()) {
			keyStates.get(key).isJustReleased = false;
		}
		mouseLeftDown = false;
		mouseRightDown = false;
	}

	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouse.x = screenX;
		mouse.y = screenY;
		if (button == Input.Buttons.LEFT) mouseLeftDown = true;
		if (button == Input.Buttons.RIGHT) mouseRightDown = true;
		return false;
	}

	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mouse.x = screenX;
		mouse.y = screenY;
		if (button == Input.Buttons.LEFT) mouseLeftDown = false;
		if (button == Input.Buttons.RIGHT) mouseRightDown = false;
		return false;
	}

	@Override public boolean keyDown(int keycode) {

		if (keyStates.containsKey(keycode)) {
			keyStates.get(keycode).isPressed = true;
		}

		return super.keyDown(keycode);
	}

	@Override public boolean keyUp(int keycode) {

		if (keyStates.containsKey(keycode)) {
			keyStates.get(keycode).isPressed = false;
			keyStates.get(keycode).isJustReleased = true;
		}

		return super.keyDown(keycode);
	}
}
