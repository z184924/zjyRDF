<template>
  <div>
    <div
      class="canvas"
      ref="canvas"
      v-bind:style="{height:modelerHeight+'px'}"
    ></div>
    <div
      id="js-properties-panel"
      class="panel"
    ></div>
    <el-button @click="saveDiagram">BPMN diagram</el-button>
    <el-button @click="saveSVG">SVG image</el-button>
    <a ref="downloadLink"></a>
    
  </div>
</template>
<script>
/*左边工具栏以及编辑节点的样式*/
import 'bpmn-js/dist/assets/diagram-js.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css';
/*右边工具栏样式*/
import 'bpmn-js-properties-panel/dist/assets/bpmn-js-properties-panel.css';

import $ from "jquery"
import BpmnViewer from 'bpmn-js'
import BpmnModeler from 'bpmn-js/lib/Modeler'
import propertiesPanelModule from 'bpmn-js-properties-panel'
import propertiesProviderModule from 'bpmn-js-properties-panel/lib/provider/camunda'
import camundaModdleDescriptor from 'camunda-bpmn-moddle/resources/camunda'
import customTraslate from '@/../static/i18n/bpmn-js-customTraslate.js'

export default {
  data() {
    return {
      // bpmn建模器
      bpmnModeler: null,
      container: null,
      canvas: null,
      modelerHeight: $(window).height() - 60 - 60 - 16 - 20 - 40 - 16 - 41,
    }
  },
  methods: {
    createNewDiagram() {
      const bpmnXmlStr = '<?xml version="1.0" encoding="UTF-8"?>\n' +
        '<bpmn:definitions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="Definitions_0fppxr8" targetNamespace="http://bpmn.io/schema/bpmn">\n' +
        '  <bpmn:process id="Process_1" isExecutable="false">\n' +
        '  </bpmn:process>\n' +
        '  <bpmndi:BPMNDiagram id="BPMNDiagram_1">\n' +
        '    <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1">\n' +
        '    </bpmndi:BPMNPlane>\n' +
        '  </bpmndi:BPMNDiagram>\n' +
        '</bpmn:definitions>\n'
      // 将字符串转换成图显示出来
      this.bpmnModeler.importXML(bpmnXmlStr, function (err) {
        if (err) {
          console.error(err);
        }
        else {
          // 这里还没用到这个，先注释掉吧
          // that.success()
        }
      })
    },
    saveSVG() {
      this.bpmnModeler.saveSVG((err, svg) => {
        this.setEncoded(this.$refs.downloadLink, 'diagram.svg', err ? null : svg);
      });
    },
    saveDiagram(done) {
      this.bpmnModeler.saveXML({ format: true }, (err, xml) => {
        this.setEncoded(this.$refs.downloadLink, 'diagram.bpmn', err ? null : xml);
      });
    },
    setEncoded(link, name, data) {
      var encodedData = encodeURIComponent(data);
      if (data) {
        link.href='data:application/bpmn20-xml;charset=UTF-8,' + encodedData
        link.setAttribute('download',name)
        link.click()
      } else {
        link.removeClass('active');
      }
    },
  },
  mounted() {
    window.onresize = () => {
      return (() => {
        this.modelerHeight = $(window).height() - 60 - 60 - 16 - 20 - 40 - 16 - 41
      })()
    }
    // 获取到属性ref为“content”的dom节点
    this.container = this.$refs.content
    // 获取到属性ref为“canvas”的dom节点
    const canvas = this.$refs.canvas

    var customTranslateModule = { translate: ['value', customTraslate] }

    // 建模，官方文档这里讲的很详细
    this.bpmnModeler = new BpmnModeler({
      container: canvas,
      //添加控制板
      propertiesPanel: {
        parent: '#js-properties-panel'
      },
      additionalModules: [
        customTranslateModule,
        // 左边工具栏以及节点
        propertiesProviderModule,
        // 右边的工具栏
        propertiesPanelModule,
      ],
      moddleExtensions: {
        camunda: camundaModdleDescriptor
      },

    });
    this.createNewDiagram(this.bpmnModeler);
  }
}
</script>
<style lang="scss">
.containers {
  position: absolute;
  background-color: #ffffff;
  width: 100%;
}
.canvas {
  width: 100%;
}
.panel {
  position: absolute;
  right: 0;
  top: 0;
  width: 300px;
}
</style>

