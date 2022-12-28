package models;

import function.IFunction;
import math.Vector3;
import third.IModel;
import third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Function implements IModel {

    private float startX, finishX, startY, finishY, startZ, finishZ, step;
    private IFunction function;

    public Function(IFunction function, float startX, float finishX, float startY, float finishY, float startZ, float finishZ, float step) {
        this.function = function;
        this.startX = startX;
        this.finishX = finishX;
        this.startY = startY;
        this.finishY = finishY;
        this.startZ = startZ;
        this.finishZ = finishZ;
        this.step = step;
    }


    @Override
    public List<PolyLine3D> getLines() {
        float mZ = Float.MIN_VALUE;
        float MZ = Float.MAX_VALUE;
        List<PolyLine3D> lines = new ArrayList<>();
        for (float i = startX; i < finishX - step; i += step) {
            for (float j = startY; j < finishY - step; j += step) {
                try {
                    mZ = Math.min(finishZ, Math.max(mZ, function.compute(i,j, startZ, finishZ)));
                    MZ = Math.max(startZ, Math.min(MZ, function.compute(i,j, startZ, finishZ)));
                    lines.add(new PolyLine3D(Arrays.asList(
                            new Vector3(i, j, function.compute(i, j, startZ, finishZ)),
                            new Vector3(i + step, j, function.compute(i + step, j, startZ, finishZ)),
                            new Vector3(i + step, j + step, function.compute(i + step, j + step, startZ, finishZ)),
                            new Vector3(i, j + step, function.compute(i, j + step, startZ, finishZ))
                    ), true));
                } catch (IllegalArgumentException e) {
                    //TODO crete what to do if graphic not exist in this coordinates;
                }

            }
        }
        for (PolyLine3D p:
                lines) {
            int color = (int) (255f * p.getPoints().get(0).getZ() / (mZ - MZ));
            color = Math.max(0,Math.min(color,255));
            p.setColor(new Color(85 ,0, 255- color));
        }
        return lines;
    }


    public void setStartX(float startX) {
        this.startX = startX;
    }


    public void setFinishX(float finishX) {
        this.finishX = finishX;
    }


    public void setStartY(float startY) {
        this.startY = startY;
    }


    public void setFinishY(float finishY) {
        this.finishY = finishY;
    }


    public void setStartZ(float startZ) {
        this.startZ = startZ;
    }


    public void setFinishZ(float finishZ) {
        this.finishZ = finishZ;
    }


    public void setStep(float step) {
        this.step = step;
    }

    public IFunction getFunction() {
        return function;
    }

    public void setFunction(IFunction function) {
        this.function = function;
    }
}
