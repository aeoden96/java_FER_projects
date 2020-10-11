package hr.fer.zemris.java.hw07.shell.namebuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * Parser creates NB objects, which are needed to store part of a filename that will be later 
 * created. It uses this kind of objects and not Strings because parser doesn't yet know
 * how will file name look like for each file.
 * @author Mateo
 *
 */
public class NameBuilderParser {
	/**
	 * Class gets a part of a string.If that part is a regulat string ,than it just saves it
	 * when execute is called for each file.If that string is between "${"  and "}" ,than 
	 * it means that in that place a group is to be placed from regex expresion,so 
	 * fuunction execute calls a function inside info object to get that specific
	 * group. That group is then concatenated to StringBuilder as regular strings are. 
	 * @author Mateo
	 *
	 */
	private class NameBuilderIm implements NameBuilder {
		/**
		 * Saves part of a string (still not processed)
		 */
		String part;
		
		/**
		 * Constructor gets a part of a string.It is still unknown what kind of a string is it.
		 * @param p
		 */
		public NameBuilderIm(String p) {
			this.part = p;

			
		}
		
		/**
		 * Function basicaly concatenates parts of a new filename .
		 * Function is called for each file,because each file has its own info object.
		 * Function desides what kind of a string is it. If its a regular string
		 * it is just passed to StringBuilder,and if its a group ,it then
		 * uses a info object to get required group from the name.
		 */
		@Override
		public void execute(NameBuilderInfo info) {
			StringBuilder x = info.getStringBuilder();
			String part=new String (this.part);
			
			if (part.charAt(0) == '$') {
				part = part.substring(2, part.length() - 1);
				String[] parts = part.trim().split(",");
				
				switch (parts.length) {
				case 2:
					part=info.getGroup(Integer.parseInt(parts[0])).trim();
					int num=Integer.parseInt(part);
					int howMuch=Integer.parseInt(parts[1].trim());
					
					part=String.format("%0" + howMuch + "d", num);
					
					break;
				case 1:
					
					part=info.getGroup(Integer.parseInt(parts[0]));
					break;

				default:
					throw new IllegalArgumentException();
				}
			}
			x.append(part);

		}

	}

	/**
	 * This implementation is used to call every other NameBuilder .This one doesnt 
	 * save any strings,but saves references to every NameBuilder created.
	 * So when its turn for next file. This one gets called,and it calls
	 * execute command over every NB object(in order in which they were created).
	 * @author Mateo
	 *
	 */
	private class NameBuilderLast implements NameBuilder {
		/**
		 * Stores references to every NB object.
		 */
		List<NameBuilder> list;
		/**
		 * Public contructor gets a list of references.
		 * @param list list of NB references
		 */
		public NameBuilderLast(List<NameBuilder> list) {
			this.list = list;
		}

		/**
		 * Function calls execute function for every NameBuilder object in a list,
		 * and thats how it construucts entire filename for each file.
		 */
		@Override
		public void execute(NameBuilderInfo info) {
			for (NameBuilder i : list) {
				i.execute(info);
			}
		}

	}

	/**
	 * NameBuilder object that saves references to every NB object created before it.
	 */
	NameBuilder x;
	/**
	 * Function gets izraz as argument ,and parses it ,so that aech chunk of that 
	 * string is saved in on of the NameBuilder objects.
	 * Finaly NameBuilderLast is created to save all those references to created NB objects.
	 * @param izraz
	 */
	public NameBuilderParser(String izraz) {

		List<NameBuilder> list = new ArrayList<>();
		String part = new String(izraz);
		
		while (true) {
			int index = part.indexOf('$');
			if (index == -1) {
				if (part.length() != 0) {
					list.add(new NameBuilderIm(part));
				}
				
				x = new NameBuilderLast(list);
				return;
			} else {
				list.add(new NameBuilderIm(part.substring(0, index)));
				part = part.substring(index);

				int index2 = part.indexOf('}');
				if (index2 == -1) {
					throw new IllegalArgumentException();
				}
				list.add(new NameBuilderIm(part.substring(0, index2 + 1)));
				part = part.substring(index2 + 1);

			}
		}
	}

	/**
	 * Function returns a NB object which stores all references to NBs created for that
	 * file name. Its function execute will go through each created NB object
	 * and activate its execute function
	 * @return returns last NameBuilder object
	 */
	public NameBuilder getNameBuilder() {

		return x;

	}
}
