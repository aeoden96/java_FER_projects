package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Iplementacija Command klase. Služi tome da se sačuva jedno stanje kod
 * kasnijeg grananja.
 * 
 * @author Mateo
 *
 */
public class PushCommand implements Command {
	/**
	 * Funkcija sa stoga kopira zadnje stanje i tu kopiju stavlja opet na stog,kako
	 * bi se stanje sačuvalo za kasnije grananje.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState temp = ctx.getCurrentState();

		ctx.pushState(temp);
	}

}
