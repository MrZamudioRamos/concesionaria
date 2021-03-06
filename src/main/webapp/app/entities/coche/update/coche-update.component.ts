import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ICoche, Coche } from '../coche.model';
import { CocheService } from '../service/coche.service';
import { IVenta } from 'app/entities/venta/venta.model';
import { VentaService } from 'app/entities/venta/service/venta.service';

@Component({
  selector: 'jhi-coche-update',
  templateUrl: './coche-update.component.html',
})
export class CocheUpdateComponent implements OnInit {
  isSaving = false;

  ventasSharedCollection: IVenta[] = [];

  editForm = this.fb.group({
    id: [],
    marca: [],
    modelo: [],
    color: [],
    numeroSerie: [],
    precio: [],
    exposicion: [],
    venta: [],
  });

  constructor(
    protected cocheService: CocheService,
    protected ventaService: VentaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ coche }) => {
      this.updateForm(coche);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const coche = this.createFromForm();
    if (coche.id !== undefined) {
      this.subscribeToSaveResponse(this.cocheService.update(coche));
    } else {
      this.subscribeToSaveResponse(this.cocheService.create(coche));
    }
  }

  trackVentaById(index: number, item: IVenta): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICoche>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(coche: ICoche): void {
    this.editForm.patchValue({
      id: coche.id,
      marca: coche.marca,
      modelo: coche.modelo,
      color: coche.color,
      numeroSerie: coche.numeroSerie,
      precio: coche.precio,
      exposicion: coche.exposicion,
      venta: coche.venta,
    });

    this.ventasSharedCollection = this.ventaService.addVentaToCollectionIfMissing(this.ventasSharedCollection, coche.venta);
  }

  protected loadRelationshipsOptions(): void {
    this.ventaService
      .query()
      .pipe(map((res: HttpResponse<IVenta[]>) => res.body ?? []))
      .pipe(map((ventas: IVenta[]) => this.ventaService.addVentaToCollectionIfMissing(ventas, this.editForm.get('venta')!.value)))
      .subscribe((ventas: IVenta[]) => (this.ventasSharedCollection = ventas));
  }

  protected createFromForm(): ICoche {
    return {
      ...new Coche(),
      id: this.editForm.get(['id'])!.value,
      marca: this.editForm.get(['marca'])!.value,
      modelo: this.editForm.get(['modelo'])!.value,
      color: this.editForm.get(['color'])!.value,
      numeroSerie: this.editForm.get(['numeroSerie'])!.value,
      precio: this.editForm.get(['precio'])!.value,
      exposicion: this.editForm.get(['exposicion'])!.value,
      venta: this.editForm.get(['venta'])!.value,
    };
  }
}
