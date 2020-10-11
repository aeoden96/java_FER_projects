package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
/**
 * Iplementira Command klasu. U njoj se zadnje stanje s stoga briše.
 * @author Mateo
 *
 */
public class PopCommand implements Command {
	/**
	 * Funkcija samo briše posljednje stanje s stoga.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {

		ctx.popState();
		

	}

}
