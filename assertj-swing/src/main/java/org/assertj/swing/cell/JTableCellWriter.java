/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.cell;

import java.awt.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JTable;

import org.assertj.swing.annotation.RunsInEDT;

/**
 * Edits the value of a cell in a {@code JTable}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@RunsInEDT
public interface JTableCellWriter {
  /**
   * Enters the given value at the given cell of the {@code JTable}. To edit a cell using this method, it is not
   * necessary to call {@link #startCellEditing(JTable, int, int)} or {@link #stopCellEditing(JTable, int, int)}.
   *
   * @param table the target {@code JTable}.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param value the value to enter.
   * @throws IllegalStateException if the {@code JTable} is disabled.
   * @throws IllegalStateException if the {@code JTable} is not showing on the screen.
   * @throws IllegalStateException if the {@code JTable} cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws org.assertj.swing.exception.ActionFailedException if an editor for the given cell cannot be found or cannot
   *           be activated.
   */
  void enterValue(@Nonnull JTable table, int row, int column, @Nonnull String value);

  /**
   * Starts editing the given cell of the {@code JTable}. This method should be called before manipulating the AWT or
   * Swing {@code Component} returned by {@link #editorForCell(JTable, int, int)}.
   *
   * @param table the target {@code JTable}.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the {@code JTable} is disabled.
   * @throws IllegalStateException if the {@code JTable} is not showing on the screen.
   * @throws IllegalStateException if the {@code JTable} cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws org.assertj.swing.exception.ActionFailedException if an editor for the given cell cannot be found or cannot
   *           be activated.
   * @see #editorForCell(JTable, int, int)
   */
  void startCellEditing(@Nonnull JTable table, int row, int column);

  /**
   * Stops editing the given cell of the {@code JTable}. This method should be called after manipulating the AWT or
   * Swing {@code Component} returned by {@link #editorForCell(JTable, int, int)}.
   *
   * @param table the target {@code JTable}.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the {@code JTable} is disabled.
   * @throws IllegalStateException if the {@code JTable} is not showing on the screen.
   * @throws IllegalStateException if the {@code JTable} cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws org.assertj.swing.exception.ActionFailedException if an editor for the given cell cannot be found or cannot
   *           be activated.
   * @see #editorForCell(JTable, int, int)
   */
  void stopCellEditing(@Nonnull JTable table, int row, int column);

  /**
   * Cancels editing the given cell of the {@code JTable}. This method should be called after manipulating the AWT or
   * Swing {@code Component} returned by @link #editorForCell(JTable, int, int)}.
   *
   * @param table the target {@code JTable}.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the {@code JTable} is disabled.
   * @throws IllegalStateException if the {@code JTable} is not showing on the screen.
   * @throws IllegalStateException if the {@code JTable} cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws org.assertj.swing.exception.ActionFailedException if an editor for the given cell cannot be found or cannot
   *           be activated.
   * @see #editorForCell(JTable, int, int)
   */
  void cancelCellEditing(@Nonnull JTable table, int row, int column);

  /**
   * <p>
   * Returns the AWT or Swing {@code Component} used as editor of the given cell. To manipulate the returned
   * {@code Component}, {@link #startCellEditing(JTable, int, int)} should be called first.
   * </p>
   *
   * <p>
   * Example:
   * </p>
   *
   * <pre>
   * Component editor = writer.editorForCell(table, 6, 8);
   * // assume editor is a JTextField
   * JTextComponentFixture editorFixture = new JTextComponentFixture(robot, (JTextField) editor);
   * writer.{@link #startCellEditing(JTable, int, int) startCellEditing}(table, 6, 8);
   * editorFixture.enterText(&quot;Hello&quot;);
   * writer.{@link #stopCellEditing(JTable, int, int) stopCellEditing}(table, 6, 8);
   * </pre>
   *
   * @param table the target {@code JTable}.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the {@code Component} used as editor of the given cell.
   * @throws IllegalStateException if the {@code JTable} cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @Nullable
  Component editorForCell(@Nonnull JTable table, int row, int column);
}
