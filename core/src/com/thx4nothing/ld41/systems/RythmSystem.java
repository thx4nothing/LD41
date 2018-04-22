package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.systems.IntervalSystem;

public class RythmSystem extends IntervalSystem {

	public int tick = 0;

	public RythmSystem(float interval) {
		super(interval);
	}

	@Override protected void updateInterval() {
		if (tick >= 0 && tick <= 2) tick++;
		else tick = 0;

	}
}
