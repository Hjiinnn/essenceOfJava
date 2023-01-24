package essence.ch7;

class Tv1 {
	boolean power;	// 전원상태(on/off)
	int channel;	// 채널
	
	void power() {
		power = !power;
	}
	
	void channelUp() {
		++channel;
	}
	
	void channelDown() {
		--channel;
	}
}

class VCR {
	boolean power;	// 전원상태(on/off)
	int counter = 0;
	
	void power() {
		power = !power;
	}
	void play() {}
	void stop() {}
	void rew() {}
	void ff() {}
}

class TVCR extends Tv1 {
	VCR vcr = new VCR();	// VCR클래스를 포함시킨다.
	
	void play() {
		vcr.play();
	}
	
	void stop() {
		vcr.stop();
	}
	
	void rew() {
		vcr.rew();
	}
	
	void ff() {
		vcr.ff();
	}

}
