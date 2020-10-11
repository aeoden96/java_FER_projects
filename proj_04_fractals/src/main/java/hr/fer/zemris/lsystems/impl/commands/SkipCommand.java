package hr.fer.zemris.lsystems.impl.commands;


import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * Implementacija Command klase.Radi isto što i DrawCommand,samo što ne ispisuje
 * liniju na ekran.
 * 
 * @author Mateo
 *
 */
public class SkipCommand implements Command {
	/**
	 * sadrži trenutni korak
	 */
	private double step;

	/**
	 * inicijalizira trenutni korak
	 * 
	 * @param step
	 */
	public SkipCommand(double step) {
		super();
		this.step = step;
	}

	/**
	 * Uzima zadnje stanje s stoga,te "ispisuje" liniju u smjeru kornjače .Tada u
	 * novi state upisuje novu poziciju i to pusha na stog.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState temp = ctx.getCurrentState();
		double orgX = temp.getPosition().getX();
		double orgY = temp.getPosition().getY();
		double destX = orgX + temp.getDirection().getX() * temp.getEffStep();
		double destY = orgY + temp.getDirection().getY() * temp.getEffStep();

		TurtleState modified = new TurtleState(
				new Vector2D(destX, destY),
				temp.getDirection(),
				temp.getColor(),
				temp.getEffStep()*step);
		ctx.popState();
		ctx.pushState(modified);

	}

}
