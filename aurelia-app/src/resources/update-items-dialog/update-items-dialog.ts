import { bindable } from 'aurelia-framework';

import * as Api from '../api/items';

import './update-items-dialog.scss';

export class UpdateItemsDialog {

  /**
   * Bound function reference to call to persist the updated item.
   *
   * @memberof UpdateItemsDialog
   * @bindable
   */
  @bindable save: (item: Api.Item) => Promise<void>;

  /**
   * Input element value of the item name that is to be updated
   *
   * @type {string}
   * @memberof UpdateItemsDialog
   */
  public itemName: string;

  /**
   * Array of new attributes of the updated item
   *   The attributes key and value inputs are bound to the elements of the array
   *
   * @type {Array<Array<string>>}
   * @memberof UpdateItemsDialog
   */
  public attributes: Array<Array<string>> = [];

  /**
   * Add empty attribute to attributes array
   *
   * @memberof UpdateItemsDialog
   */
  addEmptyAttribute(): void {
    this.attributes.push(['', '']);
  }

  /**
   * Removes the attribute from item
   *
   * @memberof UpdateItemsDialog
   */
  removeAttribute(index) {
    this.attributes.splice(index, 1);
  }

  /**
   * Updates current item
   *
   * @return {*}  {Promise<void>}
   * @memberof UpdateItemsDialog
   */
  async updateItem(): Promise<void> {
    void await this.save({
      name: this.itemName,
      attributes: Object.fromEntries(this.attributes)
    });
    this.resetDialog();
  }

  /**
   * Resets the update item form
   *
   * @memberof UpdateItemsDialog
   */
  resetDialog() {
    this.itemName = '';
    this.attributes = [];
  }

  /**
   * Getter for all attributes valid
   *
   * @readonly
   * @memberof UpdateItemsDialog
   */
  get validateAttributes() {
    return this.attributes.every(([key, value]) => {
      return key && value
    });
  }


}
