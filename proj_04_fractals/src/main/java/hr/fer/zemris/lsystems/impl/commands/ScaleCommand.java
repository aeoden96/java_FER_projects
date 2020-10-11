package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Implementira Command klasu . Koristi faktor da bi se trenutni korak
 * izmijenio.
 * 
 * @author Mateo
 *
 */
public class ScaleCommand implements Command {
	/**
	 * čuva faktor 
	 */
	private double factor;
	/**
	 * inicijalizira se definirani faktor
	 * @param factor
	 */
	public ScaleCommand(double factor) {
		super();
		this.factor = factor;
	}
	/**
	 * Funkcija uzima zadnje stanje s stoga i pomoću njega stvar novi
	 * u koji će pohraniti izmijenjenu veličiu koraka. 
	 * Takvo stanje stavlja natrag na stog.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState temp = ctx.getCurrentState();

		TurtleState modified = new TurtleState(temp.getPosition(), temp.getDirection(), temp.getColor(),
				temp.getEffStep() * factor);

		ctx.popState();
		ctx.pushState(modified);

	}

}
