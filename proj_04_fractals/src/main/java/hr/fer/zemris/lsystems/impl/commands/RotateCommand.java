package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * Iplementacija Command klase,služi za pohranu naredbe koja za definirani kut
 * rotira kornjaču.
 * 
 * @author Mateo
 *
 */
public class RotateCommand implements Command {
	/**
	 * sadrži definirani kut
	 */
	private double angle;

	/**
	 * inicijalizira se definirani kut pomoću konstruktora
	 * 
	 * @param angle
	 */
	public RotateCommand(double angle) {
		super();
		this.angle = angle;
	}

	/**
	 * funkcija miče sas stoga zadnje stanje,te ga mijenja ,tj.mijenja smjer
	 * kornjače te ga takvog vraća natrag na stog
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState temp = ctx.getCurrentState();

		Vector2D newDir = temp.copy().getDirection();
		newDir.rotate(angle);

		TurtleState modified = new TurtleState(
				temp.getPosition(),
				newDir,
				temp.getColor(),
				temp.getEffStep());

		ctx.popState();
		ctx.pushState(modified);

	}

}
