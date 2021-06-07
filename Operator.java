// 실행 클래스
public class Operator {
	Connector connector;
	OwnerFrame ownerFrame;
	PlusFrame plusFrame;
	MinusFrame minusFrame;

	public static void main(String[] args) {
		Operator operator = new Operator();
		operator.connector = new Connector(operator);
		operator.ownerFrame = new OwnerFrame(operator);
		operator.plusFrame = new PlusFrame(operator);
		operator.minusFrame = new MinusFrame(operator);

	}

}
