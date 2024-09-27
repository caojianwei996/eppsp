package learn.caojw.eppsp.controller.business;

import learn.caojw.eppsp.business.domain.Feedback;
import learn.caojw.eppsp.business.domain.Statistics;
import learn.caojw.eppsp.business.service.IBusinessService;
import learn.caojw.eppsp.common.core.domain.AjaxResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 空气质量公众监督反馈信息表 前端控制器
 * </p>
 *
 * @author CaoJianwei
 * @since 2024-09-27
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/business")
public class BusinessController {
    private final IBusinessService businessService;

    @GetMapping("/aqi")
    public AjaxResult selectAqi() {
        return AjaxResult.success(businessService.selectAqi());
    }

    @PostMapping("/feedback")
    public AjaxResult insertFeedback(Feedback feedback) {
        businessService.insertFeedback(feedback);
        return AjaxResult.success();
    }

    @GetMapping("/feedback/supervisor")
    public AjaxResult selectFeedbackBySupervisor() {
        return AjaxResult.success(businessService.selectFeedbackBySupervisor());
    }

    @GetMapping("/feedback")
    public AjaxResult selectFeedback(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum) {
        return AjaxResult.success(businessService.selectFeedback(pageSize, pageNum));
    }

    @GetMapping("/feedback/id/{id}")
    public AjaxResult selectFeedbackById(@PathVariable("id") Long id) {
        return AjaxResult.success(businessService.selectFeedbackById(id));
    }

    @PutMapping("/feedback")
    public AjaxResult updateFeedback(Feedback feedback) {
        businessService.updateFeedback(feedback);
        return AjaxResult.success();
    }

    @GetMapping("/feedback/grid")
    public AjaxResult selectFeedbackByGrid() {
        return AjaxResult.success(businessService.selectFeedbackByGrid());
    }

    @GetMapping("/city")
    public AjaxResult selectCity() {
        return AjaxResult.success(businessService.selectCity());
    }

    @PostMapping("/statistics")
    public AjaxResult insertStatistics(@RequestBody Statistics statistics) {
        businessService.insertStatistics(statistics);
        return AjaxResult.success();
    }

    @DeleteMapping("/statistics")
    public AjaxResult deleteStatistics(@RequestParam("id") Long id) {
        businessService.deleteStatistics(id);
        return AjaxResult.success();
    }

    @GetMapping("/statistics")
    public AjaxResult selectStatistics() {
        return AjaxResult.success(businessService.selectStatistics());
    }

    @GetMapping("/statistics/{id}")
    public AjaxResult selectStatistics(@PathVariable("id") Long id) {
        return AjaxResult.success(businessService.selectStatisticsById(id));
    }
}
