/*******************************************************************************
 * This file is part of Minebot.
 *
 * Minebot is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Minebot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Minebot.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package net.famzangl.minecraft.minebot.ai.commands;

import net.famzangl.minecraft.minebot.ai.AIHelper;
import net.famzangl.minecraft.minebot.ai.command.AICommand;
import net.famzangl.minecraft.minebot.ai.command.AICommandInvocation;
import net.famzangl.minecraft.minebot.ai.command.AICommandParameter;
import net.famzangl.minecraft.minebot.ai.command.ParameterType;
import net.famzangl.minecraft.minebot.ai.command.SafeStrategyRule;
import net.famzangl.minecraft.minebot.ai.path.TreePathFinder;
import net.famzangl.minecraft.minebot.ai.strategy.AIStrategy;
import net.famzangl.minecraft.minebot.ai.strategy.PathFinderStrategy;
import net.famzangl.minecraft.minebot.build.block.WoodType;

@AICommand(helpText = "Gets wood", name = "minebot")
public class CommandLumberjack {
	public static class TreePathFinderStrategy extends PathFinderStrategy {

		private TreePathFinder treeFinder;

		public TreePathFinderStrategy(TreePathFinder pathFinder,
				String description) {
			super(pathFinder, description);
			treeFinder = pathFinder;
		}
		
		@Override
		public void searchTasks(AIHelper helper) {
			super.searchTasks(helper);
		}
	}
	
	@AICommandInvocation(safeRule = SafeStrategyRule.DEFEND_MINING)
	public static AIStrategy run(
			AIHelper helper,
			@AICommandParameter(type = ParameterType.FIXED, fixedName = "lumberjack", description = "") String nameArg,
			@AICommandParameter(type = ParameterType.ENUM, description = "wood type", optional = true) WoodType type,
			@AICommandParameter(type = ParameterType.FIXED, fixedName = "replant", description = "", optional = true) String replant) {
		return new TreePathFinderStrategy(
				new TreePathFinder(type, replant != null), "Getting some " + (type == null ? "wood" : type.toString().toLowerCase()));
	}

}
