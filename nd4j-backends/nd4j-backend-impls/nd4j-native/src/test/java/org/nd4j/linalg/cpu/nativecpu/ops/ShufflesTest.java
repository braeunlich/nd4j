package org.nd4j.linalg.cpu.nativecpu.ops;

import org.junit.Ignore;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import static org.junit.Assert.assertEquals;

/**
 * Created by raver119 on 05.08.16.
 */
@Ignore
public class ShufflesTest {



    @Test
    public void testSimpleShuffle1() {
        INDArray array = Nd4j.zeros(10, 10);
        for (int x = 0; x < 10; x++) {
            array.getRow(x).assign(x);
        }

        System.out.println(array);


        System.out.println();

        Nd4j.shuffle(array, 1);

        System.out.println(array);

    }

    @Test
    public void testSymmetricShuffle4() throws Exception {
        INDArray features = Nd4j.zeros(10, 3, 4, 2);
        INDArray labels = Nd4j.zeros(10, 5);

        for (int x = 0; x < 10; x++) {
            features.slice(x).assign(x);
            labels.slice(x).assign(x);
        }

//        System.out.println(features);

        System.out.println();

        DataSet ds = new DataSet(features, labels);
        ds.shuffle();

//        System.out.println(features);

        System.out.println("------------------");


        for (int x = 0; x < 10; x++) {
            double val = features.slice(x).getDouble(0);
            INDArray row = labels.slice(x);

            for (int y = 0; y < row.length(); y++ ) {
                assertEquals(val, row.getDouble(y), 0.001);
            }
        }
    }


    @Test
    public void testSymmetricShuffle4F() throws Exception {
        INDArray features = Nd4j.create(new int[] {10, 3, 4, 2} , 'f');
        INDArray labels = Nd4j.create(new int[] {10, 5}, 'f');

        for (int x = 0; x < 10; x++) {
            features.slice(x).assign(x);
            labels.slice(x).assign(x);
        }


        System.out.println("features.length: " + features.length());

        System.out.println(labels);

        System.out.println();

        DataSet ds = new DataSet(features, labels);
        ds.shuffle();

        System.out.println(labels);

        System.out.println("------------------");


        for (int x = 0; x < 10; x++) {
            double val = features.slice(x).getDouble(0);
            INDArray row = labels.slice(x);

            for (int y = 0; y < row.length(); y++ ) {
                assertEquals(val, row.getDouble(y), 0.001);
            }
        }
    }
}
