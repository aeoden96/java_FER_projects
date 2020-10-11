package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * Iplementacija klase Command.Klasa se koristi da bi nactao liniju u
 * prozoru,definirane orijentacije i početnog položaja i boje, koji su
 * pohranjeni navrh stoga.
 * 
 * @author Mateo
 *
 */
public class DrawCommand implements Command {
	/**
	 * sadrži trenutni korak
	 */
	private double step;

	/**
	 * konstruktor inicijalizira trenutni korak
	 * 
	 * @param step
	 */
	public DrawCommand(double step) {
		super();
		this.step = step;
	}

	/**
	 * funkcija miče sa stoga zadnje stanje, te ispisuje liniju od početka ,u smjeru
	 * orijentacije. Na kraju, u novo stanje upisuje novu lokaciju te to sprema na
	 * stog.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {

		TurtleState temp = ctx.getCurrentState();
		double orgX = temp.getPosition().getX();
		double orgY = temp.getPosition().getY();
		double destX = orgX + temp.getDirection().getX() * temp.getEffStep();
		double destY = orgY + temp.getDirection().getY() * temp.getEffStep();
		String col = temp.getColor();
		col = "#" + col;

		painter.drawLine(orgX, orgY, destX, destY, Color.decode(col), 1);
		TurtleState modified = new TurtleState(
				new Vector2D(destX, destY),
				temp.getDirection(),
				temp.getColor(),
				temp.getEffStep()*step);
		ctx.popState();
		ctx.pushState(modified);
	}

}
