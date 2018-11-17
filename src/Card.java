
/**
 * A Card consists of a Suit and a Rank. 
 * This class overrides Object's equals method, as well as provides
 * its own natural ordering.
 * 
 * @author Carrie
 */
public class Card implements Comparable<Card>{
	
	/**
	 * Rank of the cards in ascending order.
	 */
	public enum Rank{
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		TEN,
		JACK,
		QUEEN,
		KING,
		ACE;
	}
	
	/**
	 * Suit of the cards, by convention, in ascending order.
	 */
	public enum Suit{
		CLUBS,
		DIAMONDS,
		HEARTS,
		SPADES;
	}
	
	//================================================================================

	public final Suit suit;
	public final Rank rank;
	
	public Card(Suit s, Rank r) {
		suit = s;
		rank = r;
	}
	
	/**
	 * Method to get a full deck of cards, already shuffled!
	 * @return
	 */
	public static CyclicList<Card> getDeck(){
		CyclicList<Card> list = new CyclicList<Card>();
		
		// get suit and rank of every card, and add to the list
		for (Suit s: Suit.values()) {
			for (Rank r: Rank.values()) {
				list.add(new Card(s, r));
			}
		}
		
		list.shuffle();
		return list;
	}
	
	/**
	 * Overrides Object's equals method. A card is equal to another
	 * card, essentially, if their suits and ranks are the same.
	 */
	@Override
	public boolean equals(Object o) {
		//TODO
		return false;
	}
	
	/**
	 * Based on Java's object contract, overriding Object.equals() 
	 * means that Object.hashCode() must also be overridden.
	 */
	@Override
	public int hashCode() {
		//TODO
		return 0;
	}

	/**
	 * Typically, a card is compared to another based on both suit 
	 * and rank. But in this case, we customize it so that just the
	 * rank of the cards are comparable. We compare them in the 
	 * position that they appear in the Rank enum, which orders them
	 * in ascending order.
	 */
	@Override
	public int compareTo(Card o) {
		//TODO
		return 0;
	}
	
	
}
