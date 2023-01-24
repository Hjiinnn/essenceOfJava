package essence.ch7;

public class DeckTest {

	public static void main(String[] args) {
		
		// 카드 한벌(Deck)을 만든다.
		Deck d = new Deck();
		// 섞기 전 제일 위의 카드를 뽑는다.
		Card c = d.pick(0);
		System.out.println(c);
		
		// 카드를 섞는다.
		d.shuffle();
		// 섞은 후 제일 위의 카드를 뽑는다.
		c = d.pick(0);
		System.out.println(c);
	}

}

/**
 * Deck 클래스
 */
class Deck {
	final int CARD_NUM = 52;				// 카드의 개수
	Card cardArr[] = new Card[CARD_NUM];	// 카드 객체배열을 포함 (카드 한 벌은 각각의 카드를 갖는다. (has-a : 포함)
	
	// 초기화
	Deck() {
		int i=0;
		
		// 무늬별 숫자별 52개의 카드 객체 배열 생성
		for(int k=Card.KIND_MAX; k > 0; k--) {
			for(int n=0; n < Card.NUM_MAX; n++) {
				cardArr[i++] = new Card(k, n+1);
			}
		}
	}
	
	// 지정된 위치에 있는 카드 하나를 꺼내서 반환
	Card pick(int index) {
		return cardArr[index];
	}
	
	// 랜덤으로 카드를 하나를 꺼내서 반환
	Card pick() {
		int index = (int)(Math.random() * CARD_NUM);
		return pick(index);
	}
	
	// 카드의 순서를 섞는다.
	void shuffle() {
		for(int i=0; i < cardArr.length; i++) {
			int r = (int)(Math.random() * CARD_NUM);
			
			Card temp = cardArr[i];
			cardArr[i] = cardArr[r];
			cardArr[r] = temp;
		}
	}
}

/**
 * Card 클래스
 */
class Card {
	static final int KIND_MAX	= 4;		// 카드 무늬의 수
	static final int NUM_MAX	= 13;		// 무늬별 카드 수
	
	static final int SPADE		= 4;
	static final int DIAMOND	= 3;
	static final int HEART		= 2;
	static final int CLOVER		= 1;
	
	int kind;
	int number;
	
	Card() {
		this(SPADE, 1);
	}
	
	Card(int kind, int number) {
		this.kind = kind;
		this.number = number;
	}
	
	public String toString() {
		String[] kinds = {"", "CLOVER", "HEART", "DIAMOND", "SPADE"};
		String numbers = "0123456789XJQK";
		
		return "kind : " + kinds[this.kind] + ", number : " + numbers.charAt(this.number);
	}
}