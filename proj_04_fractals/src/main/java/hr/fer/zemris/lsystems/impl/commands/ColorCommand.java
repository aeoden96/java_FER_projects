package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Iplementacija Command klase,služi za promjenu boje prilikom stvaranja
 * fraktala.
 * 
 * @author Mateo
 *
 */
public class ColorCommand implements Command {
	/**
	 * sadrži definiranu boju kao string
	 */
	private String color;

	/**
	 * inicijalizira se boja kao string
	 * 
	 * @param color
	 */
	public ColorCommand(String color) {
		super();
		this.color = color;
	}

	/**
	 * Execute miče sa stoga zadnje stanje,te stavnja novo ,u kojem je 
	 * nova boja.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState temp = ctx.getCurrentState();

		TurtleState modified = new TurtleState(temp.getPosition(),
				temp.getDirection(),
				color,
				temp.getEffStep());

		ctx.popState();
		ctx.pushState(modified);

	}

}
